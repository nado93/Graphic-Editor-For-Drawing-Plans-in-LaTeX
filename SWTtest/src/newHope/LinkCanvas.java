package newHope;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


public class LinkCanvas  {

	Point p1=null;
	Point p2=null;
	Composite canvasContainer;
	Color black;
	OvalCounter counter;
	
	public LinkCanvas(Composite parent,OvalCounter counter) {
		this.canvasContainer=parent;
	    black=parent.getDisplay().getSystemColor(SWT.COLOR_BLACK);
	    this.counter=counter;

		// TODO Auto-generated constructor stub
	}
	
	public void setLine(Point p1,Point p2) {
		this.p1=p1;
		this.p2=p2;
	}
	
	public void addlistener() {
		canvasContainer.addListener(SWT.MouseDoubleClick, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if (p1 == null) {
					for (int i = 0; i < counter.getListOval().size(); i++) {
						Point p=counter.getListOval().get(i).getP();
						if ((p.x - 11 < event.x && event.x < p.x + 11) && (p.y - 11 < event.y && event.y < p.y + 11)) {
							p1=p;
						}	
					}			
				}else if(p2==null) {
					for (int i = 0; i < counter.getListOval().size(); i++) {
						Point p=counter.getListOval().get(i).getP();
						if ((p.x - 11 < event.x && event.x < p.x + 11) && (p.y - 11 < event.y && event.y < p.y + 11)) {
							p2=p;
							drawLine();
						}	
					}
				}
			}
		});
	}
	
	

	public void drawLine() {
		canvasContainer.addPaintListener(new PaintListener() {
			
			
			@Override
			public void paintControl(PaintEvent e) {
				// TODO Auto-generated method stub
				e.gc.setLineWidth(1);
				e.gc.setForeground(black);
				Path path = new Path(canvasContainer.getDisplay());
				path.moveTo((float) (p1.x), (float) (p1.y));
				Point temp1=p1;
				Point temp2=p2;
				
				
				if (p1.y > p2.y) {
					Point temp = null;
					temp = temp1;
					temp1 = temp2;
					temp2 = temp;
					//path.quadTo(temp1.y, temp2.x, temp2.x, temp2.y);
					path.quadTo(temp1.x, temp2.y, temp1.x, temp1.y);

					//path.quadTo(p2.x, p1.y, p2.x, p2.y);

				} else {
					path.quadTo(temp2.x, temp1.y, temp2.x, temp2.y);
				}

				e.gc.drawPath(path);
				e.gc.dispose();
//				int arrowLength=5;
//				int arrowAngle=240;
//				
//				double theta = Math.atan2(p2.y - p1.y, p2.x - p1.x);
//			    double offset = (arrowLength - 2) * Math.cos(arrowAngle);
//				path.moveTo((float)(p2.x - arrowLength * Math.cos(theta - arrowAngle)), (float)(p2.y - arrowLength * Math.sin(theta - arrowAngle)));
//		        path.lineTo((float)p2.x, (float)p2.y);
//			    path.lineTo((float)(p2.x - arrowLength * Math.cos(theta + arrowAngle)), (float)(p2.y - arrowLength * Math.sin(theta + arrowAngle)));	
//			    path.moveTo((float)(p2.x+p1.x)/2, (float)(p2.y-50));
//			    e.gc.drawPath(path);
				
				
				
		        //path.lineTo((float)p2.x, (float)p2.y);
			    
			    // scambio p2.x con p1.y per scegliere left o right inclation for te link
			    
			    
			    
			    

			   // path.moveTo((float)(p2.x+p1.x)/2, (float)(p2.y-50));
			   // path.lineTo((float)(p1.x), (float)(p1.y ));				

			    
			    
			    
//			    int x1=p1.x;
//			    int x2=p2.x;
//			    int y1=p1.y;
//			    int y2=p2.y;
//			    
//			    int x0=(p1.x+p2.x)/2;
//			    int y0=p2.y;
//			    
//			    
//			    int r = (int)Math.sqrt((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0));
//			    int x = x0-r;
//			    int y = y0-r;
//			    int width = 2*r;
//			    int height = 2*r;
//			    int startAngle = (int) (180/Math.PI*Math.atan2(y1-y0, x1-x0));
//			    int endAngle = (int) (180/Math.PI*Math.atan2(y2-y0, x2-x0));
//			    e.gc.drawArc(x, y, width, height, 0, 180);
				
				
				//e.gc.drawLine(p1.x, p1.y+5, (int)(p2.x - offset * Math.cos(theta)), (int)(p2.y - offset * Math.sin(theta)));
			}
		});
		
	}
	

	
}
