package GUI;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import Dialog.CreateActionDialogCommand;
import Dialog.CreateGoalDialogCommand;
import Dialog.CreateSoDialogCommand;
import command.ChangeStateCommand;
import command.ElimanateStateCommand;
import logic.Action;
import logic.InitialState;


 class CreateDomainView{

	Group domainGroup;
	Shell shell;
	Group stateGroup;
	SashForm sashForm;
	Composite outer;
	Composite inside;
	Composite contentCanvas;
	Combo comboOptionInSt;
	Composite ContentInitState;
	Composite test;
	Composite test2;
	Composite ContentFinalState;
	Combo comboOptionFnst;
	Composite ContentActions;
	Combo comboOptionAction; 
	Combo[] comboAction;
	Group subOption;
	ArrayList<Action> actions=new ArrayList<>();
	
	CreateActionDialogCommand actionCommnd;
	 
	InitialState initialState=null;
	

	public CreateDomainView(SashForm sashForm) {
		this.sashForm=sashForm;
		this.shell=sashForm.getShell();
		setLayout();
		 actionCommnd=new CreateActionDialogCommand();

		
	}

	public void setLayout() {
		
		outer=new Composite(sashForm, SWT.ALL);
//		FormLayout formLayout = new FormLayout();
//		formLayout.marginHeight = 5;
//		formLayout.marginWidth = 5;
//		formLayout.spacing = 5;
//		outer.setLayout( formLayout );
		outer.setLayout(new FillLayout());	
		
		this.domainGroup = new Group(outer, SWT.BORDER);
		Font boldFont = new Font(this.domainGroup.getDisplay(), new FontData("Arial", 12, SWT.BOLD));
		this.domainGroup.setText("Domain Graph");
		this.domainGroup.setFont(boldFont);
		
		domainGroup.setLayout(new GridLayout(1, false));
		
		
		inside=new Composite(domainGroup, SWT.ALL);
	    inside.setLayout(new GridLayout(1, true));
        inside.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        


	
		
	}

	public void createContent() {

		//first group option
		
		subOption = new Group(inside, SWT.ALL);
		subOption.setText("Option");

		subOption.setLayout(new GridLayout(4, false));
	
		
		Label initialState=new Label(subOption, SWT.ALL);
		initialState.setText("Initial State: ");
		
		comboOptionInSt = new Combo (subOption, SWT.READ_ONLY);
		List<String> possibleOption=new ArrayList<String>();
		possibleOption.add("Create");
		String[] convertList=possibleOption.toArray(new String[possibleOption.size()]);
		comboOptionInSt.setItems (convertList);
		
		Rectangle clientArea = subOption.getClientArea ();
		comboOptionInSt.setBounds (clientArea.x, clientArea.y, 200, 200);
		
		
		Button bInitState=new Button(subOption, SWT.PUSH);
		Image img=new Image(shell.getDisplay(), "img/ok.png") ;
		bInitState.setImage(img);
		

		
		
		GridData gridData = new GridData(GridData.CENTER, GridData.CENTER, false, false);
		gridData.horizontalSpan = 2;
		comboOptionInSt.setLayoutData(gridData);
		
		Label finalState=new Label(subOption, SWT.ALL);
		finalState.setText("Final State: ");
		comboOptionFnst = new Combo (subOption, SWT.READ_ONLY);
		possibleOption=new ArrayList<String>();
		possibleOption.add("Create");
		convertList=possibleOption.toArray(new String[possibleOption.size()]);
		comboOptionFnst.setItems (convertList);
		comboOptionFnst.setBounds (clientArea.x, clientArea.y, 200, 200);
		Button bFnState=new Button(subOption, SWT.PUSH);
		bFnState.setImage(img);
		
		
        

		
		comboOptionFnst.setLayoutData(gridData);
	
		
		
		
		
		
		Label actionLabel=new Label(subOption, SWT.ALL);
		actionLabel.setText("Action:  ");
		
		
		comboOptionAction = new Combo (subOption, SWT.READ_ONLY);
		possibleOption=new ArrayList<String>();
		possibleOption.add("Create");
		convertList=possibleOption.toArray(new String[possibleOption.size()]);
		comboOptionAction.setItems (convertList);
		comboOptionAction.setBounds (clientArea.x, clientArea.y, 200, 200);
		gridData = new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false);
		comboOptionAction.setLayoutData(gridData);
		
		
		
		Combo combolistAction=new Combo(subOption, SWT.READ_ONLY);

		comboOptionAction.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(comboOptionAction.getText().equals("Create")) {
					combolistAction.clearSelection();
				}else {
					if(true) {
						ArrayList<String> possibleOption=new ArrayList<String>();
						for(Action action:actions) {
							possibleOption.add(action.getName());
						}
						String[] convertList=possibleOption.toArray(new String[possibleOption.size()]);
						comboOptionAction.setItems (convertList);
					}
				}
				
			}
		});
		
		
		Button bntAct=new Button(subOption, SWT.PUSH);
		bntAct.setImage(img);
		
		
		
		
		
		
		
		
		
		stateGroup=new Group(inside,SWT.NONE);
		stateGroup.setText("Items for the plan");
		stateGroup.setLayout(new GridLayout(1, true));
		GridData firstData = new GridData(SWT.FILL, SWT.FILL, true, true);
		firstData.heightHint = 750;
		stateGroup.setLayoutData(firstData);
		
		
		
		ScrolledComposite firstScroll = new ScrolledComposite(stateGroup, SWT.V_SCROLL | SWT.H_SCROLL);
	    firstScroll.setLayout(new GridLayout(1,false));
	    firstScroll.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

	    contentCanvas = new Composite(firstScroll, SWT.ALL);
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		contentCanvas.setLayout(fillLayout);
     
	    test=new Composite(contentCanvas, SWT.ALL);
	    fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		test.setLayout(fillLayout);
	   
	    test2=new Composite(contentCanvas, SWT.ALL);
	    test2.setLayout(new GridLayout(1, false));
	    test2.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));//green

	    
	    
	    ContentInitState=new Composite(test, SWT.ALL);
	    ContentInitState.setLayout(new GridLayout(1, false));
	   // ContentInitState.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    ContentInitState.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));//green
	    
	    
	    ContentFinalState=new Composite(test, SWT.ALL);
	    ContentFinalState.setLayout(new GridLayout(1, false));
	    //ContentFinalState.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    ContentFinalState.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
	   

	    
	    
	 
	
	    
   
	    ContentActions=new Composite(test2, SWT.NONE);
	    ContentActions.setLayout(new FillLayout());
	    ContentActions.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    
	  
	   
	    
	    firstScroll.setContent(contentCanvas);
	    firstScroll.setExpandHorizontal(true);
	    firstScroll.setExpandVertical(true);
	    firstScroll.setMinSize(contentCanvas.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	    
	  
		
		CreateSoDialogCommand so=new CreateSoDialogCommand();
		ElimanateStateCommand elimCmd=new ElimanateStateCommand();
		ChangeStateCommand changeCmd=new ChangeStateCommand();

		Listener buttonInLister=new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				
				so.execute(comboOptionInSt, ContentInitState);
				if(elimCmd.canExecute(comboOptionInSt)) {
					elimCmd.execute(comboOptionInSt,so.getInitialState());
				    ContentInitState.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));//green
				
				}else if(changeCmd.canExecute(comboOptionInSt)) {
					changeCmd.execute(so.getCreateSoDialog(),so.getInitialState());
					
				}
				
				
				
			}
		};
		
		CreateGoalDialogCommand goalCommand=new CreateGoalDialogCommand();
		
		Listener buttonFinLister=new Listener() {
  
        	//TODO	
			@Override
			public void handleEvent(Event event) {
			    
				goalCommand.execute(comboOptionFnst, ContentFinalState);
				if(elimCmd.canExecute(comboOptionFnst)) {
					elimCmd.execute(comboOptionFnst,goalCommand.getGoalState());
					ContentFinalState.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));//green
				
				}else if(changeCmd.canExecute(comboOptionFnst)) {
					changeCmd.execute(goalCommand.getCreateGoalDialog(),goalCommand.getGoalState());
					
				}
				
				
				
			}
		};
		
		
		comboAction=new Combo[2];
		comboAction[0]=comboOptionAction;
		comboAction[1]=combolistAction;
		
		Listener buttonActLister=new Listener() {
  
        	//TODO	
			@Override
			public void handleEvent(Event event) {
			    
				actionCommnd.execute( comboAction,ContentActions);
//				if(elimCmd.canExecute(comboOptionFnst)) {
//					elimCmd.execute(comboOptionFnst,goalCommand.getGoalState());
//					ContentFinalState.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_RED));//green
//				
//				}else if(changeCmd.canExecute(comboOptionFnst)) {
//					changeCmd.execute(goalCommand.getCreateGoalDialog(),goalCommand.getGoalState());
//					
//				}
//				
				
				
			}
		};
		
		
	    
		bInitState.addListener(SWT.Selection, buttonInLister);
		bFnState.addListener(SWT.Selection, buttonFinLister);
		bntAct.addListener(SWT.Selection, buttonActLister);



		


	}
	
	
	public Group getCompCanvas() {
		return stateGroup;
	}
}
