/*	GridFileFilter.java
	Author: William Woodruff
	-------------

	Extends FileFilter, allowing graphical file operations to identify grid files.
*/

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GridFileFilter extends FileFilter
{
	public GridFileFilter() {}

	public boolean accept(File f)
	{
		return (f.isDirectory() || f.getName().toLowerCase().endsWith("grid"));
	}

	public String getDescription()
	{
		return "CGoL Grid Files (*.grid)";
	}
}