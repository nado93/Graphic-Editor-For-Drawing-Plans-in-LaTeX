package GUI;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import Action.Action;
import Action.CanvasAction;
import Dialog.CreateActionDialogCommand;
import Dialog.CreateGoalDialogCommand;
import Dialog.CreateSoDialogCommand;
import State.InitialStateCanvas;
import command.ChangeEffCommand;
import command.ChangeNameCommand;
import command.ChangePrecCommand;
import command.EliminateActionCommand;
import logic.ContentAction;

class CreateDomainView {

	Group domainGroup;
	Shell shell;
	Group stateGroup;
	SashForm sashForm;
	Composite outer;
	Composite inside;
	Composite contentCanvas;
	Composite contentInitState;
	Composite containerInitState;
	Composite part1;
	Composite part2;
	Composite contentGoalState;
	Composite containerGoalState;
	ContentAction compositeAction;
	Composite containerAction;
	Tree treeAction;
	Group subOption;
	ArrayList<Action> actionsArray;

	MenuItem showAction;
	MenuItem showActionW;
	MenuItem elimAction;
	MenuItem modifAction;

	CreateActionDialogCommand actionCommnd;

	InitialStateCanvas initialState = null;

	public CreateDomainView(SashForm sashForm) {
		this.sashForm = sashForm;
		this.shell = sashForm.getShell();
		setLayout();
		actionCommnd = new CreateActionDialogCommand();
		actionsArray = new ArrayList<>();

	}

	public void setLayout() {

		outer = new Composite(sashForm, SWT.ALL);
		outer.setLayout(new FillLayout());

		this.domainGroup = new Group(outer, SWT.BORDER);
		Font boldFont = new Font(this.domainGroup.getDisplay(), new FontData("Arial", 12, SWT.BOLD));
		this.domainGroup.setText("Domain Graph");
		this.domainGroup.setFont(boldFont);

		domainGroup.setLayout(new GridLayout(1, false));

		inside = new Composite(domainGroup, SWT.ALL);
		inside.setLayout(new GridLayout(1, true));
		inside.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	}

	public void createContent() {

		// first group option

		subOption = new Group(inside, SWT.ALL);
		subOption.setText("Option");

		subOption.setLayout(new GridLayout(4, false));

		Label initialState = new Label(subOption, SWT.ALL);
		initialState.setText("Initial State: ");

	

	

		Button bInitState = new Button(subOption, SWT.PUSH);
		Image img = new Image(shell.getDisplay(), "img/ok.png");
		bInitState.setImage(img);

		GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
		gridData.horizontalSpan = 2;
		//comboOptionInSt.setLayoutData(gridData);

		Label finalState = new Label(subOption, SWT.ALL);
		finalState.setText("Final State: ");
		
		Button bFnState = new Button(subOption, SWT.PUSH);
		bFnState.setImage(img);

		Label actionLabel = new Label(subOption, SWT.ALL);
		actionLabel.setText("Action:  ");

		Button bntAct = new Button(subOption, SWT.PUSH);
		img = new Image(shell.getDisplay(), "img/addCond.png");
		bntAct.setImage(img);

		stateGroup = new Group(inside, SWT.NONE);
		stateGroup.setText("Items for the plan");
		stateGroup.setLayout(new GridLayout(1, true));
		GridData firstData = new GridData(SWT.FILL, SWT.FILL, true, true);
		firstData.heightHint = 750;
		stateGroup.setLayoutData(firstData);

		ScrolledComposite firstScroll = new ScrolledComposite(stateGroup, SWT.V_SCROLL | SWT.H_SCROLL);
		firstScroll.setLayout(new GridLayout(1, false));
		firstScroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		contentCanvas = new Composite(firstScroll, SWT.ALL);
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		contentCanvas.setLayout(fillLayout);

		part1 = new Composite(contentCanvas, SWT.ALL);
		fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		part1.setLayout(fillLayout);

		contentInitState = new Composite(part1, SWT.BORDER);
		//contentInitState.setLayout(new GridLayout(1, false));
		//contentInitState.setLayout(new FillLayout());
		
		containerInitState=new Composite(contentInitState, SWT.BORDER);
		containerInitState.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
		containerInitState.setLayout(new FillLayout());
		//containerInitState.setLayout(new GridLayout(1, false));

		containerInitState.setLocation(50,80);
//		containerInitState.setSize(60, 150);


		contentGoalState = new Composite(part1, SWT.BORDER);
		//contentGoalState.setLayout(new GridLayout(1, false));
		containerGoalState=new Composite(contentGoalState, SWT.BORDER);
		containerGoalState.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
		containerGoalState.setLayout(new FillLayout());
//		containerGoalState.setLocation((3*contentGoalState.getSize().x)/2,20);
		containerGoalState.setLocation(70,80);

		

		part2 = new Composite(contentCanvas, SWT.ALL);
		part2.setLayout(new GridLayout(3, true));

		final ScrolledComposite composite = new ScrolledComposite(part2, SWT.V_SCROLL);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		treeAction = new Tree(composite, SWT.BORDER);
		final Menu menu = new Menu(treeAction);
		treeAction.setToolTipText("Created Action");
		treeAction.setMenu(menu);

		MenuItem[] items = menu.getItems();
		for (int i = 0; i < items.length; i++) {
			items[i].dispose();
		}
		showAction = new MenuItem(menu, SWT.PUSH);
		showAction.setText("Draw Action");

		elimAction = new MenuItem(menu, SWT.PUSH);
		elimAction.setText("Eliminate Action");

		modifAction = new MenuItem(menu, SWT.CASCADE);
		modifAction.setText("Modify...");

		Menu subMenu = new Menu(menu);
		modifAction.setMenu(subMenu);

		MenuItem modifName = new MenuItem(subMenu, SWT.PUSH);
		modifName.setText("Name");

		MenuItem modifPrec = new MenuItem(subMenu, SWT.PUSH);
		modifPrec.setText("Preconditions");

		MenuItem modifEff = new MenuItem(subMenu, SWT.PUSH);
		modifEff.setText("Effects");

		composite.setContent(treeAction);
		composite.setExpandHorizontal(true);
		composite.setExpandVertical(true);
		composite.setAlwaysShowScrollBars(true);
		composite.setMinSize(treeAction.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		compositeAction = new ContentAction(part2, SWT.BORDER);
		gridData = new GridData(GridData.FILL, GridData.FILL, false, false);
		gridData.horizontalSpan = 2;
		compositeAction.setLayoutData(gridData);
		
		firstScroll.setContent(contentCanvas);
		firstScroll.setExpandHorizontal(true);
		firstScroll.setExpandVertical(true);
		firstScroll.setMinSize(contentCanvas.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		
		

		
//		Display.getDefault().timerExec(100, new Runnable() {
//		    @Override
//		    public void run() {
//		    	containerAction.redraw();
//		      
//		      // Run again - 
//		      Display.getDefault().timerExec(100, this);
//		    }
//		   });
		
	
		
		showAction.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {

				//TODO posso creare solo un azione,dovrei ripulire ad ogni nuovo comando
				
				TreeItem[] actions = treeAction.getSelection();
				
//				if(containerAction.getChildren().length>0) {
//					containerAction.redraw();
//				}
				if (actions.length > 0) {
					TreeItem actionItem = getRoot(actions[0]);
					Action action = findAction(actionItem.getText());
					if(containerAction!=null) {
						containerAction.dispose();
					}
					containerAction=new Composite(compositeAction, SWT.BORDER);
					containerAction.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
					containerAction.setLayout(new FillLayout());
					containerAction.setLocation(40,150);
					
					CanvasAction canvasAction=new CanvasAction(containerAction,SWT.ALL,action);
					action.setPaint(canvasAction);
					canvasAction.draw();
					canvasAction.addDNDListener();
					compositeAction.setPaintAction(canvasAction);
					
				}
					
			}
		});

		elimAction.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem[] actions = treeAction.getSelection();
				if (actions.length > 0) {

					TreeItem actionItem = getRoot(actions[0]);
					Action action = findAction(actionItem.getText());
					actionItem.dispose();
					actionsArray.remove(action);
				}

			}
		});

		modifName.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem[] actions = treeAction.getSelection();
				if (actions.length > 0) {
					TreeItem actionItem = getRoot(actions[0]);
					Action action = findAction(actionItem.getText());
					ChangeNameCommand cmd = new ChangeNameCommand();
					cmd.execute(actionItem, action);

				}

			}
		});

		modifPrec.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem[] actions = treeAction.getSelection();
				if (actions.length > 0) {
					TreeItem actionItem = getRoot(actions[0]);
					Action action = findAction(actionItem.getText());
					ChangePrecCommand cmd = new ChangePrecCommand();
					cmd.execute(action, actionItem);

				}

			}
		});

		modifEff.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				TreeItem[] actions = treeAction.getSelection();
				if (actions.length > 0) {
					TreeItem actionItem = getRoot(actions[0]);
					Action action = findAction(actionItem.getText());
					ChangeEffCommand cmd = new ChangeEffCommand();
					cmd.execute(action, actionItem);

				}

			}
		});

		CreateSoDialogCommand so = new CreateSoDialogCommand();
		Listener buttonInLister = new Listener() {

			@Override
			public void handleEvent(Event event) {
				so.execute(containerInitState);
			}
		};

		CreateGoalDialogCommand goalCommand = new CreateGoalDialogCommand();
		Listener buttonFinLister = new Listener() {

			
			@Override
			public void handleEvent(Event event) {
				goalCommand.execute(containerGoalState);

			}
		};

		EliminateActionCommand elimAct = new EliminateActionCommand();

		Listener buttonActLister = new Listener() {

			@Override
			public void handleEvent(Event event) {
				actionCommnd.execute(treeAction, actionsArray);
				elimAct.execute(actionsArray);
				subOption.pack();
			}	
		};

		bInitState.addListener(SWT.Selection, buttonInLister);
		bFnState.addListener(SWT.Selection, buttonFinLister);
		bntAct.addListener(SWT.Selection, buttonActLister);
		
		
	
		

	}

	public TreeItem getRoot(TreeItem a) {
		while (a.getParentItem() instanceof TreeItem) {
			a = a.getParentItem();
		}
		return a;
	}

	public Action findAction(String actionName) {
		for (int i = 0; i < actionsArray.size(); i++) {
			if (actionsArray.get(i).getName().equals(actionName)) {
				return actionsArray.get(i);
			}
		}
		return null;
	}
}
