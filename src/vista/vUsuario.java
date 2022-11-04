package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.daousuario;
import modelo.Usuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class vUsuario extends JFrame {
	DefaultTableModel modelo=new DefaultTableModel();
    ArrayList<Usuario> lista=new ArrayList<Usuario>();
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtNombre;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JTable tblUsuarios;
	private JLabel lblID;
	private JScrollPane scrollPane;
	daousuario dao=new daousuario();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public vUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(10, 21, 46, 14);
		contentPane.add(lblid);
		
		lblID = new JLabel("1");
		lblID.setBounds(107, 21, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 49, 72, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(107, 46, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblpassword = new JLabel("PASSWORD");
		lblpassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblpassword.setBounds(10, 90, 86, 14);
		contentPane.add(lblpassword);
		
		JLabel lblNewLabel_2 = new JLabel("NOMBRE");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(10, 129, 72, 14);
		contentPane.add(lblNewLabel_2);
		
		txtPassword = new JTextField();
		txtPassword.setText("");
		txtPassword.setBounds(107, 77, 86, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(107, 127, 86, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario user=new Usuario();
					user.setUser(txtUser.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtNombre.getText());
					if(dao.insertarUsuario(user)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
					
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAgregar.setBounds(7, 182, 89, 23);
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEditar.setBounds(107, 182, 89, 23);
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminar.setBounds(305, 182, 89, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 216, 384, 210);
		contentPane.add(scrollPane);
		
		tblUsuarios = new JTable();
		tblUsuarios.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblUsuarios);
		modelo.addColumn("ID");
		modelo.addColumn("USER");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("NOMBRE");
		tblUsuarios.setModel(modelo);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBorrar.setBounds(206, 182, 89, 23);
		contentPane.add(btnBorrar);
	
	}
	public void actualizarTabla() {
		while(modelo.getRowCount()<0) {
			modelo.removeRow(0);
		}
		lista=dao.fetchUsuarios();
		for(Usuario u: lista) {
			Object o[]=new Object[4];
			o[0]=u.getId();
			o[1]=u.getUser();
			o[2]=u.getPassword();
			o[3]=u.getNombre();
			modelo.addRow(o);
		}
		tblUsuarios.setModel(modelo);
	}
}
