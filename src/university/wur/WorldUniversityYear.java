/*******************************************************************************
 * Caleydo - visualization for molecular biology - http://caleydo.org
 *
 * Copyright(C) 2005, 2012 Graz University of Technology, Marc Streit, Alexander
 * Lex, Christian Partl, Johannes Kepler University Linz </p>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>
 *******************************************************************************/
package university.wur;

import static demo.RankTableDemo.toFloat;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.caleydo.core.view.opengl.layout.Column.VAlign;
import org.caleydo.core.view.opengl.layout2.renderer.GLRenderers;
import org.caleydo.vis.rank.data.AFloatFunction;
import org.caleydo.vis.rank.data.FloatInferrers;
import org.caleydo.vis.rank.data.IFloatSetterFunction;
import org.caleydo.vis.rank.model.FloatRankColumnModel;
import org.caleydo.vis.rank.model.IRow;
import org.caleydo.vis.rank.model.RankTableModel;
import org.caleydo.vis.rank.model.StackedRankColumnModel;
import org.caleydo.vis.rank.model.StarsRankColumnModel;
import org.caleydo.vis.rank.model.mapping.PiecewiseMapping;

import com.google.common.base.Function;

/**
 * @author Samuel Gratzl
 *
 */
public class WorldUniversityYear {
	public static final int COL_QSSTARS = 0;
	public static final int COL_overall = 1;
	public static final int COL_academic = 2;
	public static final int COL_employer = 3;
	public static final int COL_faculty = 4;
	public static final int COL_international = 5;
	public static final int COL_internationalstudents = 6;
	public static final int COL_citations = 7;
	public static final int COL_arts = 8;
	public static final int COL_engineering = 9;
	public static final int COL_life = 10;
	public static final int COL_natural = 11;
	public static final int COL_social = 12;

	private float qsstars;
	private float overall;
	private float academic;
	private float employer;
	private float faculty;
	private float international;
	private float internationalstudents;
	private float citations;
	private float arts;
	private float engineering;
	private float life;
	private float natural;
	private float social;

	public WorldUniversityYear(String[] l) {
		qsstars = toFloat(l, 2);
		overall = toFloat(l, 3);
		academic = toFloat(l, 4);
		employer = toFloat(l, 5);
		faculty = toFloat(l, 6);
		international = toFloat(l, 7);
		internationalstudents = toFloat(l, 8);
		citations = toFloat(l, 9);
		arts = toFloat(l, 10);
		engineering = toFloat(l, 11);
		life = toFloat(l, 12);
		natural = toFloat(l, 13);
		social = toFloat(l, 14);
	}

	public float get(int index) {
		switch (index) {
		case COL_academic:
			return academic;
		case COL_arts:
			return arts;
		case COL_citations:
			return citations;
		case COL_employer:
			return employer;
		case COL_engineering:
			return engineering;
		case COL_faculty:
			return faculty;
		case COL_international:
			return international;
		case COL_internationalstudents:
			return internationalstudents;
		case COL_life:
			return life;
		case COL_natural:
			return natural;
		case COL_overall:
			return overall;
		case COL_QSSTARS:
			return qsstars;
		case COL_social:
			return social;
		}
		return Float.NaN;
	}

	public void set(int index, float value) {
		switch (index) {
		case COL_academic:
			academic = value;
			break;
		case COL_arts:
			arts = value;
			break;
		case COL_citations:
			citations = value;
			break;
		case COL_employer:
			employer = value;
			break;
		case COL_engineering:
			engineering = value;
			break;
		case COL_faculty:
			faculty = value;
			break;
		case COL_international:
			international = value;
			break;
		case COL_internationalstudents:
			internationalstudents = value;
			break;
		case COL_life:
			life = value;
			break;
		case COL_natural:
			natural = value;
			break;
		case COL_overall:
			overall = value;
			break;
		case COL_QSSTARS:
			qsstars = value;
			break;
		case COL_social:
			social = value;
			break;
		}
	}

	public static StackedRankColumnModel addYear(RankTableModel table, String title,
			Function<IRow, WorldUniversityYear> year, boolean addStars, boolean asIs) {

		// * Academic reputation (40%)
		// * Employer reputation (10%)
		// * Faculty/student ratio (20%)
		// * Citations per faculty (20%)
		// * International faculty ratio (5%)
		// * International student ratio (5%)
		// Color[] light = StephenFewColorPalette.getAsAWT(EBrightness.LIGHT);
		// Color[] dark = StephenFewColorPalette.getAsAWT(EBrightness.MEDIUM);
		// stacked.add(col(year, COL_academic, "Academic reputation", dark[1], light[1]));
		// stacked.add(col(year, COL_employer, "Employer reputation", dark[2], light[2]));
		// stacked.add(col(year, COL_faculty, "Faculty/student ratio", dark[3], light[3]));
		// stacked.add(col(year, COL_citations, "Citations per faculty", dark[4], light[4]));
		// stacked.add(col(year, COL_international, "International faculty ratio", dark[5], light[5]));
		// stacked.add(col(year, COL_internationalstudents, "International student ratio", dark[6], light[6]));
		List<FloatRankColumnModel> cols = new ArrayList<>();
		cols.add(col(year, COL_academic, "Academic reputation", "#FC9272", "#FEE0D2"));
		cols.add(col(year, COL_employer, "Employer reputation", "#9ECAE1", "#DEEBF7"));
		cols.add(col(year, COL_faculty, "Faculty/student ratio", "#A1D99B", "#E5F5E0"));
		cols.add(col(year, COL_citations, "Citations per faculty", "#C994C7", "#E7E1EF"));
		cols.add(col(year, COL_international, "International faculty ratio", "#FDBB84", "#FEE8C8"));
		cols.add(col(year, COL_internationalstudents, "International student ratio", "#DFC27D", "#F6E8C3"));

		final StackedRankColumnModel stacked;
		if (asIs) {
			stacked = null;
			for (FloatRankColumnModel col : cols)
				table.add(col);
		} else {
			stacked = new StackedRankColumnModel();
			stacked.setTitle(title);
			table.add(stacked);
			for (FloatRankColumnModel col : cols)
				stacked.add(col);

			stacked.setWeights(new float[] { 40, 10, 20, 20, 5, 5 });
			stacked.setWidth(380);
		}
		if (addStars) {
			StarsRankColumnModel s = new StarsRankColumnModel(new ValueGetter(year, COL_QSSTARS), GLRenderers.drawText(
					"QS Stars", VAlign.CENTER), Color.decode("#FECC5C"), Color.decode("#FFFFB2"), 6);
			table.add(s);
		}
		return stacked;
	}

	public static void addSpecialYear(RankTableModel table, Function<IRow, WorldUniversityYear> year) {

		FloatRankColumnModel c;
		c = col(year, COL_arts, "Arts & Humanities", "#FFD92F", "#FFFFCC");
		table.add(c);
		c.hide();
		c = col(year, COL_engineering, "Engineering & Technology", "#8DA0CB", "#ECE2F0");
		table.add(c);
		c.hide();
		c = col(year, COL_life, "Life Sciences & Medicine", "#E78AC3", "#FDE0DD");
		table.add(c);
		c.hide();
		c = col(year, COL_natural, "Natural Sciences", "#A6D854", "#F7FCB9");
		table.add(c);
		c.hide();
	}

	private static FloatRankColumnModel col(Function<IRow, WorldUniversityYear> year, int col, String text,
			String color, String bgColor) {
		return col(year, col, text, Color.decode(color), Color.decode(bgColor));
	}

	private static FloatRankColumnModel col(Function<IRow, WorldUniversityYear> year, int col, String text,
			Color color, Color bgColor) {
		return new FloatRankColumnModel(new ValueGetter(year, col), GLRenderers.drawText(text, VAlign.CENTER),
 color,
				bgColor, percentage(), FloatInferrers.MEDIAN);
	}

	protected static PiecewiseMapping percentage() {
		return new PiecewiseMapping(0, 100);
	}

	public static Map<String, WorldUniversityYear[]> readData(int... years) throws IOException {
		Map<String, WorldUniversityYear[]> data = new LinkedHashMap<>();
		for (int i = 0; i < years.length; ++i) {
			String year = String.format("wur%4d.txt", years[i]);
			try (BufferedReader r = new BufferedReader(new InputStreamReader(
					WorldUniversityYear.class.getResourceAsStream(year), Charset.forName("UTF-8")))) {
				String line;
				r.readLine(); // header
				while ((line = r.readLine()) != null) {
					String[] l = line.split("\t");
					String school = l[1].trim();

					WorldUniversityYear universityYear = new WorldUniversityYear(l);
					if (!data.containsKey(school)) {
						data.put(school, new WorldUniversityYear[years.length]);
					}
					data.get(school)[i] = universityYear;
				}
			}
		}
		return data;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> readCountries() throws IOException {
		Map<String, String> result = new TreeMap<>();

		try (BufferedReader r = new BufferedReader(new InputStreamReader(
				WorldUniversityYear.class.getResourceAsStream("countries.txt"), Charset.forName("UTF-8")))) {
			String line;
			r.readLine();
			while ((line = r.readLine()) != null) {
				String[] l = line.split(";");
				String school = l[0].trim();
				String country = l[1].trim();

				result.put(school, country);
			}
		}
		return result;
	}

	static class ValueGetter extends AFloatFunction<IRow> implements IFloatSetterFunction<IRow> {
		private final Function<IRow, WorldUniversityYear> year;
		private final int subindex;

		public ValueGetter(Function<IRow, WorldUniversityYear> year, int column) {
			this.year = year;
			this.subindex = column;
		}

		@Override
		public float applyPrimitive(IRow in) {
			WorldUniversityYear y = year.apply(in);
			if (y == null)
				return Float.NaN;
			return y.get(subindex);
		}

		@Override
		public void set(IRow in, float value) {
			WorldUniversityYear y = year.apply(in);
			if (y == null)
				return;
			y.set(subindex, value);
		}
	}
}
