package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCasillas extends JPanel implements ActionListener
{
	private InterfazSudoku interfaz;
	
	private JTextField[][] campos;
	
	public PanelCasillas(InterfazSudoku pInterfaz)
	{
		interfaz = pInterfaz;

		campos = new JTextField[9][9];
		setLayout(new GridLayout(9, 9));

		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				campos[i][j] = new JTextField();
				campos[i][j].addActionListener(this);
				campos[i][j].setActionCommand("CASILLA_" + i + "_" + j);
				add(campos[i][j]);
			}
		}
	}

	public void actualizar(int[][] casillas)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				int valor = casillas[i][j];
				
				if(valor != -1)
				{
					campos[i][j].setText("" + casillas[i][j]);
				}
				else
				{
					campos[i][j].setText("");
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		
		int posi = Integer.parseInt(comando.substring(8, 9));
		int posj = Integer.parseInt(comando.substring(10, 11));
		
		String input = campos[posi][posj].getText();
		
		try
		{
			interfaz.jugar(posi, posj, input);
		}
		catch (Exception e1)
		{
			JOptionPane.showMessageDialog(interfaz, "Debe ser un número entre 1 y 9");
		}
	}

}
