/*******************************************************************************
 * Caleydo - Visualization for Molecular Biology - http://caleydo.org
 * Copyright (c) The Caleydo Team. All rights reserved.
 * Licensed under the new BSD license, available at http://caleydo.org/license
 ******************************************************************************/
package university.usnews;

import org.caleydo.core.gui.command.AOpenViewHandler;

import demo.ARcpRankTableDemoView;
import demo.RankTableDemo.IModelBuilder;

/**
 * @author Samuel Gratzl
 *
 */
public class WorldBestUniversitiesView extends ARcpRankTableDemoView {
	private static final String ID = "lineup.demo.university.wur";
	@Override
	public IModelBuilder createModel() {
		return new WorldBestUniversities();
	}

	@Override
	protected String getCopyright() {
		return "<a href=\"http://mup.asu.edu/research_data.html\">The Center for Measuring University Performance</a>";
	}

	@Override
	public String getViewGUIID() {
		return ID;
	}

	public static class Handler extends AOpenViewHandler {
		public Handler() {
			super(ID);
		}
	}


}