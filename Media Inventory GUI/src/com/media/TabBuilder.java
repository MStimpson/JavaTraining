package com.media;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 
public class TabBuilder {

	private Data d = new Data();
	public JPanel initialTab(){
		JPanel initial 	  = new JPanel();
		initial.setLayout(new GridBagLayout());
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.HORIZONTAL;
		
		String[] columnNames = {"Movie Title", "Description", "Genre", "Minutes", "Location"};
	    Map<Integer, Media> med=d.getMedia();
	    List keys = new ArrayList(med.keySet());
	    
	    Object[][] data = new Object[med.size()][12];
	    
		for(int i=0;i<keys.size();i++){
	    	Object obj = keys.get(i);
	    	int x=0;
	    		data[i][x]= med.get(obj).getTitle();
	        x++;
	    		data[i][x]= med.get(obj).getDesc();
	    	x++;
	    		data[i][x]= med.get(obj).getGenre();
	    	x++;
	    		data[i][x] = med.get(obj).getMinutes();	
	    	x++;
	    		data[i][x]=med.get(obj).getLocation();
	    }
	    JTable table = new JTable(data, columnNames);
	    table.setAutoCreateRowSorter(true);
	    table.setPreferredScrollableViewportSize(new Dimension(435, 90));
	    table.setFillsViewportHeight(true);
    //Create the scroll pane and add the table to it.
	    JScrollPane scrollPane = new JScrollPane(table);
    //Add the scroll pane to this panel.
	    gBC.gridx=0;
	    gBC.gridy=0;
	    gBC.gridwidth=3;
	    gBC.gridheight=2;
	    initial.add(scrollPane,gBC);
	    JButton viewButton = new JButton("SaveChanges");
	    gBC.ipady = 0;
        gBC.weighty = 1.0;
        gBC.anchor = GridBagConstraints.PAGE_END;
        gBC.insets = new Insets(10,0,0,0);  //Padding
        gBC.gridx = 1;
        gBC.gridwidth = 2;
        gBC.gridy = 2;
	    initial.add(viewButton,gBC);
	    
		return initial;
	}
	
	public JPanel searchTab(){
		
		JPanel search = new JPanel();
		
	    JTextField textField = new JTextField(20);
	    JButton buttonField = new JButton("Search");
	    JTable searchTable = new JTable();
	    textField.addActionListener(null);
		buttonField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if results set not null build JTable and populate with results
				
				String[] columnNames = {"Movie Title", "Description", "Genre", "Minutes", "Location"};
			    Map<Integer, Media> med=d.searchMedia(textField.getText());;
			    List keys = new ArrayList(med.keySet());
			    
			    Object[][] data = new Object[med.size()][12];
			    
				for(int i=0;i<keys.size();i++){
			    	Object obj = keys.get(i);
			    	int x=0;
			    		data[i][x]= med.get(obj).getTitle();
			        x++;
			    		data[i][x]= med.get(obj).getDesc();
			    	x++;
			    		data[i][x]= med.get(obj).getGenre();
			    	x++;
			    		data[i][x] = med.get(obj).getMinutes();	
			    	x++;
			    		data[i][x]=med.get(obj).getLocation();
			    }
			    JTable table = new JTable(data, columnNames);
			    table.setAutoCreateRowSorter(true);
			    table.setPreferredScrollableViewportSize(new Dimension(435, 90));
			    table.setFillsViewportHeight(true);
			    //Create the scroll pane and add the table to it.
			    JScrollPane scrollPane = new JScrollPane(table);
			    scrollPane.remove(table);
				//everything above is table stuff
			    search.add(scrollPane);
			    search.revalidate();
			    search.repaint();
			}
		});
		search.add(textField);
		search.add(buttonField);
    	search.add(searchTable);
		return search;
	}
	
	public JPanel addTab(){
		
		JPanel add = new JPanel();
		add.setLayout(new GridLayout(6,2));
		
		/* Here goes the add/edit   */
    	JLabel ltitle 		= new JLabel("Movie Title:");
    	JTextField jtitle 	= new JTextField(20);
    	JLabel ldesc 		= new JLabel("Description:");
    	JTextField jdesc	= new JTextField(20);
    	JLabel lgenre 		= new JLabel("Genre:");
    	JTextField jgenre 	= new JTextField(20);
    	JLabel lminutes 	= new JLabel("Minutes:");
    	JTextField jminutes = new JTextField(20);
    	JLabel llocation 	= new JLabel("Location:");
    	JTextField jlocation= new JTextField(20);
    	JLabel blankLabel 	= new JLabel();
    	JButton addButton 	= new JButton("Add");
    	
    //add labels and text fields to add JPanel
    	add.add(ltitle);
    	add.add(jtitle);
    	
    	add.add(ldesc);
    	add.add(jdesc);
    	
    	add.add(lgenre);
    	add.add(jgenre);
    	
    	add.add(lminutes);
    	add.add(jminutes);
    	
    	add.add(llocation);
    	add.add(jlocation);
    	
    	add.add(blankLabel);
    	add.add(addButton);
		
		return add;
	}
}