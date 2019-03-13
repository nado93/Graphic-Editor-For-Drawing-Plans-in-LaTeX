package Dialog;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import Action.Action;

public class CreateActionDialog extends IDialog {


	Composite compCanvas;
	List listPrec;
	List listEff;
	Text newPrec;
	Text newEff;
	Button buttonNegPrec;
	Button buttonNegEff;
	Text actionName;

	Combo combOption;
	Combo comboAction;

	ArrayList<String> prec;
	ArrayList<String> effect;
	Action action;
	Tree treeActions;
	ArrayList<Action> actions;
	

	public CreateActionDialog(Tree list,ArrayList<Action> actions) {
		super(list.getShell(),SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CENTER);
		this.treeActions=list;
		this.actions=actions;
		prec = new ArrayList<>();
		effect = new ArrayList<>();

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createDialog() {
		// TODO Auto-generated method stub
		super.createDialog();
	}

	@Override
	public void createContent() {
		label.setText("Create a new action");
		composite.setLayout(new GridLayout(2, false));

		Label lNameAct = new Label(composite, SWT.ALL);
		lNameAct.setText("Name of the action: ");

		GridData gridData = new GridData(GridData.FILL, GridData.FILL, false, false);
		// gridData.horizontalSpan = 3;
		lNameAct.setLayoutData(gridData);

		actionName = new Text(composite, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL, GridData.FILL, false, false);
		// gridData.horizontalSpan = 3;
		actionName.setLayoutData(gridData);

		Group groupPrec = new Group(composite, SWT.ALL);
		groupPrec.setText("Precondition");
		groupPrec.setLayout(new GridLayout(3, false));

		newPrec = new Text(groupPrec, SWT.BORDER);
		buttonNegPrec = new Button(groupPrec, SWT.CHECK);
		buttonNegPrec.setText("neg");

		Button addPrec = new Button(groupPrec, SWT.PUSH);
		Image icon = new Image(composite.getDisplay(), "img/addCond.png");
		addPrec.setImage(icon);
		addPrec.addListener(SWT.Selection, addPrecListener());

		listPrec = new List(groupPrec, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

		Button btnDeletePrec = new Button(groupPrec, SWT.PUSH);
		icon = new Image(groupPrec.getDisplay(), "img/deleteCond.png");
		btnDeletePrec.setImage(icon);
		btnDeletePrec.addListener(SWT.Selection, getDelPrecListener());

		Group groupEff = new Group(composite, SWT.ALL);
		groupEff.setText("Effect");
		groupEff.setLayout(new GridLayout(3, false));

		newEff = new Text(groupEff, SWT.BORDER);
		buttonNegEff = new Button(groupEff, SWT.CHECK);
		buttonNegEff.setText("neg");

		Button addEff = new Button(groupEff, SWT.PUSH);
		icon = new Image(composite.getDisplay(), "img/addCond.png");
		addEff.setImage(icon);
		addEff.addListener(SWT.Selection, addEffListener());

		listEff = new List(groupEff, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

		Button btnDeleteEff = new Button(groupEff, SWT.PUSH);
		icon = new Image(composite.getDisplay(), "img/deleteCond.png");
		btnDeleteEff.setImage(icon);
		btnDeleteEff.addListener(SWT.Selection, getDelEffListener());

		pack();
	}

	public Listener getDelPrecListener() {
		Listener buttonListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				int index = listPrec.getSelectionIndex();
				if (!(index > prec.size()) && index != -1) {
					listPrec.remove(index);
					prec.remove(index);
				}

			}
		};

		return buttonListener;

	}

	public Listener getDelEffListener() {
		Listener buttonListener = new Listener() {

			@Override
			public void handleEvent(Event event) {
				int index = listEff.getSelectionIndex();
				if (!(index > effect.size()) && index != -1) {
					listEff.remove(index);
					effect.remove(index);
				}

			}
		};

		return buttonListener;

	}

	public Listener addPrecListener() {

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {

				String cond = newPrec.getText();
				//cond=cond.toLowerCase();
				boolean isChecked = buttonNegPrec.getSelection();
				if (!(prec.contains(cond)) && !cond.equals("")) {
					if (isChecked) {
						cond = "¬" + cond;
					}
					prec.add(cond);
					listPrec.add(cond);
				}
				newPrec.setText("");
			}
		};

		return listener;
	}

	public Listener addEffListener() {

		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event event) {

				String cond = newEff.getText();
				//cond=cond.toLowerCase();
				boolean isChecked = buttonNegEff.getSelection();
				if (!(effect.contains(cond)) && !cond.equals("")) {
					if (isChecked) {
						cond = "¬" + cond;
					}
					effect.add(cond);
					listEff.add(cond);
				}
				newEff.setText("");
			}
		};

		return listener;
	}

	@Override
	public Listener getOkbtnListener() {
		Listener btn = new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (!actionName.equals("") && !isAlreadyCreated(actionName.getText())) {
						action = new Action(actionName.getText(), prec, effect);
						TreeItem item=new TreeItem(treeActions, SWT.BORDER);
						item.setText(actionName.getText());
						TreeItem childPrec = new TreeItem(item, SWT.NONE);
						childPrec.setText("Preconditions");
						TreeItem childEff = new TreeItem(item, SWT.NONE);
						childEff.setText("Effect");
						for(String pString:prec) {
							TreeItem child = new TreeItem(childPrec, SWT.NONE);
							child.setText(pString);
						}

						for(String eString:effect) {
							TreeItem child = new TreeItem(childEff, SWT.NONE);
							child.setText(eString);
						}
	
						actions.add(action);
						
						setVisible(false);
						treeActions.pack();
				}
			}
		};
		
		return btn;
	}

	public boolean isAlreadyCreated(String actionName) {
		TreeItem[] items=treeActions.getItems();

			for(int i=0;i<items.length;i++) {
				if(items[i].getText().equals(actionName))
					return true;
			}
			return false;
	}


}
