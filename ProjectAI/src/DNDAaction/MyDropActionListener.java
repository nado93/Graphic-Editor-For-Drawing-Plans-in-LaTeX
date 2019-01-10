package DNDAaction;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import Action.Action;
import Action.Node;
import DataTrasfer.MyType;
import GUI.CreateDomainView;
import GUI.DrawWindow;
import GraphPart.GraphContent;
import State.GoalStateCanvas;
import State.IState;
import State.IStateCanvas;
import State.InitialStateCanvas;

public class MyDropActionListener extends DropTargetAdapter {
	private Composite parentComposite;
	private DropTarget target;
	private ArrayList<Action> actionList;

	/**
	 * @param parentComposite - the composite that holds all pictures
	 * @param target          - the drop target
	 */
	public MyDropActionListener(Composite parentComposite, DropTarget target,ArrayList<Action> actionListobject) {
		this.parentComposite = parentComposite;
		this.target = target;
		this.actionList=actionListobject;
	}

	public void dragEnter(DropTargetEvent event) {
		if (event.detail == DND.DROP_DEFAULT) {
			event.detail = DND.DROP_COPY;
		}
	}

	public void dragOperationChanged(DropTargetEvent event) {
		if (event.detail == DND.DROP_DEFAULT) {
			event.detail = DND.DROP_COPY;
		}
	}

	@Override
	public void drop(DropTargetEvent event) {

		if (target.getControl() instanceof Composite) {

			Action action = null;
			IState state=null;
			GraphContent content = (GraphContent) target.getControl();
			Composite comp = new Composite(content, SWT.ALL);
			comp.setLayoutData(new GridData(GridData.FILL, GridData.FILL, false, false));
			comp.setLayout(new FillLayout());
			comp.setLocation(comp.toControl(event.x, event.y));
			
			if (event.data != null) {
		          MyType[] myTypes = (MyType[]) event.data;
				if (myTypes != null) {
					for (int i = 0; i < myTypes.length; i++) {
						switch (myTypes[i].getName()) {
						case "start":
							state=new IState(myTypes[i].getEff());
							InitialStateCanvas stateCanvas=new InitialStateCanvas(comp, SWT.ALL, state);
							stateCanvas.draw();
							break;
							
						case "goal":
							state=new IState(myTypes[i].getEff());
							IStateCanvas stateCanvas2=new GoalStateCanvas(comp, SWT.ALL, state);
							stateCanvas2.draw();
							break;

						default:
							
							
							for(int j=0;j<actionList.size();j++) {
								if(myTypes[i].getName().equals(actionList.get(j).getName())) {
									if(myTypes[i].getPrec().equals(actionList.get(j).getPrec()) && 
											myTypes[i].getEff().equals(actionList.get(j).getEffect())) {
										action=actionList.get(j);
										
										
									}
								}
							}
							
							Node canvas = new Node(comp, SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED,action);
							canvas.draw();
							canvas.pack();
							
							
							break;
						}
						
						
			            }

				}
			}			
			
			content.addlistener(comp);
		}
	}

}
