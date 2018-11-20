package logic;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.LineAttributes;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class InitialState {

	ArrayList<String> precPos;
	Canvas canvasSo;
	Composite contentCanvas;

	public InitialState(ArrayList<String> pos) {
		this.precPos = new ArrayList<>(pos);

	}

	// TODO pay attention for contraction, example we have A, can't be added notA
	public ArrayList<String> getPrec() {
		return precPos;

	}

	// TODO method allow to draw the initial state

	public void draw(Composite composite) {
		if (canvasSo != null) {
			canvasSo.redraw();
		} else {
			this.contentCanvas = composite;
			canvasSo = new Canvas(composite, SWT.ALL);
			// canvasSo.setLayout(new FillLayout());
			canvasSo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			canvasSo.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_YELLOW));// green
			canvasSo.setSize(composite.getSize());

		}

		canvasSo.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				int numCond = precPos.size();

				int startX = composite.getLocation().x + 40;
				int startY = composite.getLocation().y + 40;

				e.gc.setLineWidth(6);
				e.gc.drawLine(startX, startY, startX, startY + (30 * numCond));
				e.gc.setLineWidth(1);

				int posY = startY + 10;
				for (int i = 0; i < numCond; i++) {

					e.gc.drawLine(startX, posY, startX + 50, posY);
					String string = precPos.get(i);
					e.gc.drawString(string, startX + 10, posY - 20, false);

					posY = posY + 30;

				}

				// e.gc.drawRectangle(r);
			}
		});

		// composite.pack();

	}

	public void update(ArrayList<String> pos) {
		this.precPos = new ArrayList<>(pos);
	}

	public void elimanate() {
//		canvasSo.redraw();
//		canvasSo.layout();
		canvasSo.dispose();
		precPos.clear();
	}

	// TODO method that write the latex code
}
