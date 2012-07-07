/*
 * @(#)FiltroFileChooser.java	1.16 04/07/26
 * 
 * Copyright (c) 2004 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * -Redistribution of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 * 
 * -Redistribution in binary form must reproduce the above copyright notice, 
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * 
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may 
 * be used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL 
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MIDROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST 
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, 
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY 
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE, 
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * You acknowledge that this software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of any
 * nuclear facility.
 */

/*
 * @(#)FiltroFileChooser.java	1.16 04/07/26
 */

package presentacion.seleccionFicheros;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.filechooser.FileFilter;

/**
 * A convenience implementation of FileFilter that filters out all files except
 * for those type extensions that it knows about.
 * 
 * Extensions are of the type ".foo", which is typically found on Windows and
 * Unix boxes, but not on Macinthosh. Case is ignored.
 * 
 * Example - create a new filter that filerts out all files but gif and jpg
 * image files:
 * 
 * JFileChooser chooser = new JFileChooser(); FiltroFileChooser filter = new
 * FiltroFileChooser( new String{"gif", "jpg"}, "JPEG & GIF Images")
 * chooser.addChoosableFileFilter(filter); chooser.showOpenDialog(this);
 * 
 * @version 1.16 07/26/04
 * @author Jeff Dinkins
 */
public class FiltroFileChooser extends FileFilter {

	private static String TYPE_UNKNOWN = "Type Unknown";

	private static String HIDDEN_FILE = "Hidden File";

	private Hashtable filters = null;

	private String description = null;

	private String fullDescription = null;

	private boolean useExtensionsInDescription = true;

	/**
	 * Creates a file filter. If no filters are added, then all files are
	 * accepted.
	 * 
	 * @see #addExtension(String)
	 */
	public FiltroFileChooser() {
		this.filters = new Hashtable();
	}

	/**
	 * Creates a file filter that accepts files with the given extension.
	 * Example: new FiltroFileChooser("jpg");
	 * 
	 * @param extension
	 *            extensión del filtro
	 * @see #addExtension(String)
	 */
	public FiltroFileChooser(String extension) {
		this(extension, null);
	}

	/**
	 * Creates a file filter that accepts the given file type. Example: new
	 * FiltroFileChooser("jpg", "JPEG Image Images");
	 * 
	 * Note that the "." before the extension is not needed. If provided, it
	 * will be ignored.
	 * 
	 * @param extension
	 *            extensión del fichero
	 * @param description
	 *            texto con la descripción del fichero
	 * @see #addExtension(String)
	 */
	public FiltroFileChooser(String extension, String description) {
		this();
		if (extension != null)
			addExtension(extension);
		if (description != null)
			setDescription(description);
	}

	/**
	 * Creates a file filter from the given string array. Example: new
	 * FiltroFileChooser(String {"gif", "jpg"});
	 * 
	 * Note that the "." before the extension is not needed adn will be ignored.
	 * 
	 * @param filters
	 *            conjunto de filtros
	 * @see #addExtension(String)
	 */
	public FiltroFileChooser(String[] filters) {
		this(filters, null);
	}

	/**
	 * Creates a file filter from the given string array and description.
	 * Example: new FiltroFileChooser(String {"gif", "jpg"}, "Gif and JPG
	 * Images");
	 * 
	 * Note that the "." before the extension is not needed and will be ignored.
	 * 
	 * @param filters
	 *            conjunto de filtros
	 * @param description
	 *            descripción del fichero
	 * @see #addExtension(String)
	 */
	public FiltroFileChooser(String[] filters, String description) {
		this();
		for (int i = 0; i < filters.length; i++) {
			// add filters one by one
			addExtension(filters[i]);
		}
		if (description != null)
			setDescription(description);
	}

	/**
	 * Return true if this file should be shown in the directory pane, false if
	 * it shouldn't.
	 * 
	 * Files that begin with "." are ignored.
	 * 
	 * @param f
	 *            fichero
	 * @return true si el fichero se tiene que mostrar, false si no se tiene que
	 *         mostrar
	 * @see #getExtension
	 */
	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);
			if (extension != null && filters.get(getExtension(f)) != null) {
				return true;
			}
			;

		}
		return false;
	}

	/**
	 * Return the extension portion of the file's name .
	 * 
	 * @param f
	 *            fichero
	 * @return texto con la extensión del fichero
	 */
	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
			;
		}
		return null;
	}

	/**
	 * Adds a filetype "dot" extension to filter against.
	 * 
	 * For example: the following code will create a filter that filters out all
	 * files except those that end in ".jpg" and ".tif":
	 * 
	 * FiltroFileChooser filter = new FiltroFileChooser();
	 * filter.addExtension("jpg"); filter.addExtension("tif");
	 * 
	 * Note that the "." before the extension is not needed and will be ignored.
	 * 
	 * @param extension
	 *            extensión del fichero
	 */
	public void addExtension(String extension) {
		if (filters == null) {
			filters = new Hashtable(5);
		}
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	}

	/**
	 * Returns the human readable description of this filter. For example: "JPEG
	 * and GIF Image Files (*.jpg, *.gif)"
	 * 
	 * @return descripción del fichero
	 * @see #setDescription(String)
	 * @see #setExtensionListInDescription(boolean)
	 * @see #isExtensionListInDescription()
	 * @see FileFilter#getDescription()
	 */
	public String getDescription() {
		if (fullDescription == null) {
			if (description == null || isExtensionListInDescription()) {
				fullDescription = description == null ? "(" : description
						+ " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys();
				if (extensions != null) {
					fullDescription += "." + (String) extensions.nextElement();
					while (extensions.hasMoreElements()) {
						fullDescription += ", ."
								+ (String) extensions.nextElement();
					}
				}
				fullDescription += ")";
			} else {
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	/**
	 * Sets the human readable description of this filter. For example:
	 * filter.setDescription("Gif and JPG Images");
	 * 
	 * @param description
	 *            descripción del fichero
	 * @see #setDescription(String)
	 * @see #setExtensionListInDescription(boolean)
	 * @see #isExtensionListInDescription()
	 * @see #getDescription
	 */
	public void setDescription(String description) {
		this.description = description;
		fullDescription = null;
	}

	/**
	 * Determines whether the extension list (.jpg, .gif, etc) should show up in
	 * the human readable description.
	 * 
	 * Only relevent if a description was provided in the constructor or using
	 * setDescription();
	 * 
	 * @param b
	 *            indica si se pone la extensión en la descripción
	 * @see #getDescription
	 * @see #setDescription(String)
	 * @see #isExtensionListInDescription()
	 */
	public void setExtensionListInDescription(boolean b) {
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	/**
	 * Returns whether the extension list (.jpg, .gif, etc) should show up in
	 * the human readable description.
	 * 
	 * Only relevent if a description was provided in the constructor or using
	 * setDescription();
	 * 
	 * @return true si se muestra la extensión en la descripción, si no false
	 * @see #getDescription
	 * @see #setDescription(String)
	 * @see #setExtensionListInDescription(boolean)
	 */
	public boolean isExtensionListInDescription() {
		return useExtensionsInDescription;
	}
}