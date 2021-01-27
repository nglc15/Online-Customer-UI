package labs.lab9;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//Copy and paste this template into your Main.java file
class CustomerManagerUI{
	JFrame frame;
	JPanel bigPanel;
	JPanel leftPanel;
	JPanel rightPanel; 
	ArrayList<Person> personList;
	DefaultListModel nameList;
	
	public CustomerManagerUI() {
		personList = new ArrayList<Person>();
		nameList = new DefaultListModel();
		frameWorkDefault();
		
	}
	
	public void frameWorkDefault() {	
		frame = new JFrame();
		frame.setSize(800,500);
		frame.setTitle("Linus Ng 33368349");
		
		//default panel
		bigPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		bigPanel.setLayout(new GridLayout(1,2));
		leftPanel.setLayout(new BorderLayout());
		//rightPanel.setLayout(new BoxLayout());
		
		//list of Names
		JList<String> displayList = new JList(nameList);		
		leftPanel.add(displayList, BorderLayout.CENTER);
		
		
		//delete button
		JButton delButton = new JButton("Delete");
		class delListener implements ActionListener{
			public void actionPerformed(ActionEvent event)
			{
				int index = 0;
				if(displayList.getSelectedValue() == null)
				{
				}
				else
				{
					String selected = displayList.getSelectedValue();
					nameList.removeElement(selected);
					for(int i = 0; i < personList.size(); i ++)
					{
						if(personList.get(i).getName().equals(selected))
						{
							index = i;
						}
					}
					personList.remove(index);
				}
			}
		}
		
		ActionListener delL = new delListener();
		delButton.addActionListener(delL);
		JPanel delPanel = new JPanel();
		delPanel.add(delButton);
		leftPanel.add(delPanel, BorderLayout.SOUTH);
		bigPanel.add(leftPanel);

		
		//Name text
		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameField = new JTextField(20);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		rightPanel.add(namePanel);
		
		//Email text
		JPanel emailPanel = new JPanel();
		JLabel emailLabel = new JLabel("Email ");
		JTextField emailField = new JTextField(20);
		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		rightPanel.add(emailPanel);
		
		//Pets text
		JPanel petsPanel = new JPanel();
		JLabel petsLabel = new JLabel("Pets: ");
		JCheckBox dogCheck = new JCheckBox("Dog(s)");
		JCheckBox catCheck = new JCheckBox("Cat(s)");
		JCheckBox birdCheck = new JCheckBox("Bird(s)");
		JCheckBox fishCheck = new JCheckBox("Fish");
		JCheckBox otherCheck = new JCheckBox("Other");
		petsPanel.add(petsLabel);
		petsPanel.add(dogCheck);
		petsPanel.add(catCheck);
		petsPanel.add(birdCheck);
		petsPanel.add(fishCheck);
		petsPanel.add(otherCheck);
		rightPanel.add(petsPanel);
		bigPanel.add(rightPanel);
		
		//Amount Spent
		JPanel amtPanel = new JPanel();
		JLabel amtLabel = new JLabel("Total Amount Spent: ");
		JTextField amtField = new JTextField(20);
		amtField.setText("0.0");
		amtPanel.add(amtLabel);
		amtPanel.add(amtField);
		rightPanel.add(amtPanel);
		
		//Location
		JPanel locationPanel = new JPanel();
		JLabel locationLabel = new JLabel("Home Store Location: ");
		JComboBox cityLocation = new JComboBox();
		cityLocation.addItem("Irvine");
		cityLocation.addItem("Los Angeles");
		cityLocation.addItem("Paris");
		cityLocation.addItem("Shanghai");
		cityLocation.addItem("New York");
		cityLocation.addItem("London");
		locationPanel.add(locationLabel);
		locationPanel.add(cityLocation);
		rightPanel.add(locationPanel);
		
		//String location = (String) cityLocation.getSelectedItem();
		
		//Notes
		JPanel notesPanel = new JPanel();
		JLabel notesLabel = new JLabel("Notes: ");
		JTextArea notesArea = new JTextArea(3,20);
		notesArea.setEditable(true);
		notesPanel.add(notesLabel);
		notesPanel.add(notesArea);
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		JScrollPane notesScroll = new JScrollPane(notesArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		notesPanel.add(notesScroll);
		rightPanel.add(notesPanel);
				
		JPanel buttonsPanel = new JPanel();
		//save customer
		JButton saveButton = new JButton("Save Customer");
		
		class saveListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				JFrame j = new JFrame();
				if(displayList.getSelectedValue() == null)
				{
					try {
						if(nameField.getText() == "" || nameField.getText().isBlank())
						{
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else if(emailField.getText() == "" || emailField.getText().isBlank())
						{
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else if(nameList.contains(nameField.getText()))
						{
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							double temp = Double.parseDouble(amtField.getText());
							boolean[] bpets = {false, false, false, false, false};
							if(dogCheck.isSelected())
							{
								bpets[0] = true;
							}
							if(catCheck.isSelected())
							{
								bpets[1] = true;
							}
							if(birdCheck.isSelected())
							{
								bpets[2] = true;
							}
							if(fishCheck.isSelected())
							{
								bpets[3] = true;
							}
							if(otherCheck.isSelected())
							{
								bpets[4] = true;
							}
							personList.add(new Person(nameField.getText(), emailField.getText(), bpets, amtField.getText(), cityLocation.getSelectedIndex(), notesArea.getText()));
							nameList.addElement(nameField.getText());
							//sorts elements
							Collection sorter = Collections.list(nameList.elements());
							Object arr[] = sorter.toArray();
							Arrays.sort(arr);
							nameList.clear();
							for(Object s : arr)
							{
								nameList.addElement(s);
							}
							
							JOptionPane.showMessageDialog(j,"Customer saved!","Success!",JOptionPane.PLAIN_MESSAGE );
						}
					}
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					String selected = displayList.getSelectedValue();
					try
					{
						if(nameField.getText() == "" || nameField.getText().isBlank())
						{
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else if(emailField.getText() == "" || emailField.getText().isBlank())
						{
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else if(nameList.contains(nameField.getText()) && !(nameField.getText().equals(selected)))
						{
							System.out.println("lol");
							JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
						}
						else
						{	
							nameList.removeElement(selected);
							double temp = Double.parseDouble(amtField.getText());
							boolean[] bpets = {false, false, false, false, false};
							if(dogCheck.isSelected())
							{
								bpets[0] = true;
							}
							if(catCheck.isSelected())
							{
								bpets[1] = true;
							}
							if(birdCheck.isSelected())
							{
								bpets[2] = true;
							}
							if(fishCheck.isSelected())
							{
								bpets[3] = true;
							}
							if(otherCheck.isSelected())
							{
								bpets[4] = true;
							}
							personList.add(new Person(nameField.getText(), emailField.getText(), bpets, amtField.getText(), cityLocation.getSelectedIndex(), notesArea.getText()));
							nameList.addElement(nameField.getText());
							//sorts elements
							Collection sorter = Collections.list(nameList.elements());
							Object arr[] = sorter.toArray();
							Arrays.sort(arr);
							nameList.clear();
							for(Object s : arr)
							{
								nameList.addElement(s);
							}
							
							JOptionPane.showMessageDialog(j,"Customer saved!","Success!",JOptionPane.PLAIN_MESSAGE );
						}
					}
					catch(NumberFormatException e)
					{
						JOptionPane.showMessageDialog(j,"Invalid input!","Error",JOptionPane.ERROR_MESSAGE);
					}
						
					
				}
						
		
			j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			j.setVisible(true);
			

				}			
		}
		
		ActionListener saveL = new saveListener();
		saveButton.addActionListener(saveL);
		
		//new customer
		JButton newButton = new JButton("New Customer");
		
		class newListener implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				nameField.setText("");
				emailField.setText("");
				cityLocation.setSelectedIndex(0);
				amtField.setText("0.0");
				dogCheck.setSelected(false);
				catCheck.setSelected(false);
				birdCheck.setSelected(false);
				fishCheck.setSelected(false);
				otherCheck.setSelected(false);
				notesArea.setText("");
			}
		}
		
		ActionListener newL = new newListener();
		newButton.addActionListener(newL);
		
		
		buttonsPanel.add(saveButton);
		buttonsPanel.add(newButton);
		rightPanel.add(buttonsPanel);
		
		bigPanel.add(rightPanel);
		frame.add(bigPanel);
		
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		class ViewCustomerListener implements ListSelectionListener{
			public void valueChanged(ListSelectionEvent event) {
				String selected = displayList.getSelectedValue();
				for(int i = 0; i < personList.size(); i ++)
				{
					if(personList.get(i).getName().equals(selected))
					{
						nameField.setText(personList.get(i).getName());
						emailField.setText(personList.get(i).getEmail());
						amtField.setText(personList.get(i).getSpent());
						notesArea.setText(personList.get(i).getNotes());
						cityLocation.setSelectedIndex(personList.get(i).getLocation());
						
						
						dogCheck.setSelected(false);
						catCheck.setSelected(false);
						birdCheck.setSelected(false);
						fishCheck.setSelected(false);
						otherCheck.setSelected(false);
						boolean[] bpets = personList.get(i).getPets();
						if(bpets[0])
						{
							dogCheck.setSelected(true);
						}
						if(bpets[1])
						{
							catCheck.setSelected(true);
						}
						if(bpets[2])
						{
							birdCheck.setSelected(true);
						}
						if(bpets[3])
						{
							fishCheck.setSelected(true);
						}
						if(bpets[4])
						{
							otherCheck.setSelected(true);
						}
						
						
						
					}
				}
			}
		}
		
		ListSelectionListener listL = new ViewCustomerListener();
		displayList.addListSelectionListener(listL);
		
	}
	
	
	
	
	public static void main(String[] args) {
		CustomerManagerUI ui = new CustomerManagerUI();

		
	  //WRITE YOUR TEST CASES FOR ALL FUNCTIONS HERE 
	}
}
