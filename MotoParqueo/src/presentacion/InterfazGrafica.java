package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import negocio.CupoDiario;
import negocio.CupoMensual;
import negocio.Moto;
import negocio.Parqueadero;
import persistencia.CreateExlFile;
import persistencia.PrintNow;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import contabilidad.Contabilidad;
import contabilidad.ContabilidadMensual;
import contabilidad.RegistroDiario;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.input.KeyCode;
import persistencia.WinRegistry;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Color;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("unused")
public class InterfazGrafica {

	private JFrame frmMotoparqueo;
	private static Parqueadero parqueadero = null;
	private JScrollPane scrollPaneDiario;
	private JTable tableMotosDiario;
	private String[] columnastableMotosDiarioS = {"Recibo","Placa","Tipo","Locker","Cascos","Entrada","Cliente"};
	private String[] columnastHistorialS = {"Placa", "Locker", "Cantidad", "Pagado", "Tiempo","Entrada","Salida"};
	private String[] columnastMensualS = {"Nombre","Placa","Tipo","Celular", "Sig.Cobro","Mensualidad"};
	@SuppressWarnings("rawtypes")
	private Vector columnasMotosDiarioV = new Vector();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector columnastHistorialV = new Vector(Arrays.asList(columnastHistorialS));
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Vector columnastMensualV = new Vector(Arrays.asList(columnastMensualS));
	@SuppressWarnings("rawtypes")
	private Vector rowDataMotosDiario = new Vector();
	@SuppressWarnings("rawtypes")
	private Vector rowDatatHistorial = new Vector();
	@SuppressWarnings("rawtypes")
	private Vector rowDatatMensual = new Vector();
	private JTextField textoPlaca;
	private JTextField textoCascos;
	private JTable tHistorial;
	private JScrollPane scrollHistorial;
	private JTextField textoValor;
	private JLabel eTiempo;
	private JLabel ePlaca;
	private JButton btnRegistrar;
	private JButton btnCobrar;
	private JLabel lblTotalCobrado;
	private JLabel eTotalCobrado;
	private JTextField tCierre;
	private JTextField tCons;
	private JLabel lblConsecutivo;
	private JButton btnTest;
	private JTextField txtBan;
	private JTextField txtUnBan;
	private JTable tMensual;
	private JScrollPane scrollMensual;
	private JLabel lblMenNombre;
	private JTextField txtMenNombre;
	private JLabel lblMenCedula;
	private JLabel lblMenCelular;
	private JLabel lblMenPlaca;
	private JLabel lblMenIngreso;
	private JTextField txtMenCelular;
	private JTextField txtMenPlaca;
	private JTextField txtMenIngreso;
	private JButton btnMenIngresar;
	private JTextField txtMenCedula;
	private JTextField txtMenPlacaPago;
	private JLabel lblMenPlacaPago;
	private JButton btnPagoMensual;
	private JTextField txtMenRetirar;
	private JButton btnMenRetirar;
	private JPopupMenu popupMenu = new JPopupMenu();
	private JPopupMenu popupMenuDiario = new JPopupMenu();
	private JTextField txtAdmMH;
	private JTextField txtAdmUH;
	private JTextField txtAdmPH;
	private JTextField txtAdmFecha;
	private JTextField txtAdmPlaca;
	private JTextField txtAdmIngreso;
	private JTextField txtAdmSalida;
	private JTextField txtAdmVC;
	private JButton btnAdmCerrarDiaEspecial;
	private JButton btnAdmIngresar;
	private JPanel tabAdmHDiario;
	private JTextField txtCarroporHora;
	private JTextField txtCarroporFraccion;
	private JTextField txtCarrotFraccion;
	private JTextField txtConNumLock;
	private JTextField txtConPref;
	private JTextField txtMenMensualidadIn;
	@SuppressWarnings("rawtypes")
	private JComboBox comboMenTipo;
	private JTextField txtPrecioCascos;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					WinRegistry.inicializarConfig();
					List<String> preferidos;
					preferidos = new ArrayList<String>(); 
					String preferido = WinRegistry.leerConfig("Lockers", "preferidos");
					String[] preferArray = preferido.split(":");
					for(int i=0; i<preferArray.length; i++){
						preferidos.add(preferArray[i]);
					}
					parqueadero = new Parqueadero(Integer.parseInt(WinRegistry.leerConfig("Lockers", "numLock")), preferidos);
					InterfazGrafica window = new InterfazGrafica();
					window.frmMotoparqueo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		File backups = new File("backups");
		if(!backups.exists()){
			Utilidades.createFolder("backups");
		}
		if(parqueadero.getContabilidadMensual()==null){
			parqueadero.setContabilidadMensual(new ContabilidadMensual());
		}
		frmMotoparqueo = new JFrame();
		frmMotoparqueo.getContentPane().setBackground(Color.BLACK);
		frmMotoparqueo.setFont(new Font("Arial", Font.PLAIN, 18));
		frmMotoparqueo.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parqueadero.guardar();
			}
			@Override
			public void windowOpened(WindowEvent e) {
				Parqueadero cargado = parqueadero.cargar();
				if(cargado!=null){
					parqueadero = cargado;
				}
				actualizarTMotoDiario();
				actualizarTMensual();
				actualizarTHistorial();
				GregorianCalendar hoy = new GregorianCalendar();
				String cobranza = "Los siguientes clientes deben mensualidad:\n";
				for(CupoMensual next: parqueadero.getCuposMensuales()){
					if(next.getFechaSiguienteCobro().getTimeInMillis()<hoy.getTimeInMillis()){
						cobranza = cobranza.concat(next.getCliente().getNombre()+" Placa:"+next.getCliente().getPlaca()+".\n");
					}
				}
				if (cobranza.length()>43) {
					JOptionPane.showMessageDialog(null, cobranza);
				}
			}
		});
		frmMotoparqueo.setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGrafica.class.getResource("/recursos/Icono Parqueadero 32.jpg")));
		frmMotoparqueo.setTitle("OPYTRANS LTDA.");
		frmMotoparqueo.setResizable(false);
		frmMotoparqueo.setBounds(10, 10, 1346, 710);
		frmMotoparqueo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMotoparqueo.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 1340, 681);
		frmMotoparqueo.getContentPane().add(tabbedPane);
		
		rowDataMotosDiario = new Vector();
		columnasMotosDiarioV = new Vector(Arrays.asList(columnastableMotosDiarioS));
		tableMotosDiario = new JTable(rowDataMotosDiario, columnasMotosDiarioV);
		tableMotosDiario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMotosDiario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JOptionPane.showMessageDialog(null, "Funciono");				
			}
		});
		tableMotosDiario.setFont(new Font("Arial", Font.PLAIN, 20));
		JPanel panelDiario = new JPanel();
		panelDiario.setBorder(null);
		panelDiario.setBackground(Color.RED);
		panelDiario.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				actualizarTMotoDiario();
				textoPlaca.requestFocus();
			}
		});
		tabbedPane.addTab("Diario", null, panelDiario, null);
		tabbedPane.setEnabledAt(0, true);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setBackgroundAt(0, Color.YELLOW);
		panelDiario.setLayout(null);
		
		scrollPaneDiario = new JScrollPane();
		scrollPaneDiario.setBounds(10, 11, 1015, 614);
		panelDiario.add(scrollPaneDiario);
		scrollPaneDiario.setViewportView(getTableMotosDiario());
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnRegistrar.addActionListener(new ActionListener() {
			@SuppressWarnings({ })
			public void actionPerformed(ActionEvent e) {
				procesoPrincipal();
			}
		});
		btnRegistrar.setBounds(1200, 87, 120, 40);
		panelDiario.add(btnRegistrar);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setForeground(Color.WHITE);
		lblValor.setFont(new Font("Arial", Font.PLAIN, 40));
		lblValor.setBounds(1035, 231, 92, 47);
		panelDiario.add(lblValor);
		
		textoValor = new JTextField();
		textoValor.setFont(new Font("Arial", Font.PLAIN, 40));
		textoValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCobrar.requestFocus();
			}
		});
		textoValor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textoValor.selectAll();
			}
		});
		textoValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textoValor.setText("0");
		textoValor.setBounds(1147, 228, 173, 53);
		panelDiario.add(textoValor);
		textoValor.setColumns(10);
		
		eTiempo = new JLabel("Tiempo transcurrido");
		eTiempo.setForeground(Color.WHITE);
		eTiempo.setFont(new Font("Arial", Font.PLAIN, 20));
		eTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		eTiempo.setBounds(1035, 292, 285, 24);
		panelDiario.add(eTiempo);
		
		btnCobrar = new JButton("Cobrar");
		btnCobrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cobrar();
			}
		});
		btnCobrar.setBounds(1191, 354, 129, 40);
		panelDiario.add(btnCobrar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(1035, 150, 285, 2);
		panelDiario.add(separator);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printCobrar();
			}
		});
		btnImprimir.setBounds(1035, 354, 146, 40);
		panelDiario.add(btnImprimir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(1035, 177, 285, 47);
		panelDiario.add(panel_1);
		panel_1.setLayout(null);
		
		ePlaca = new JLabel("0");
		ePlaca.setBounds(112, 0, 173, 47);
		ePlaca.setForeground(Color.WHITE);
		panel_1.add(ePlaca);
		ePlaca.setFont(new Font("Arial", Font.PLAIN, 40));
		ePlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblPlaca_1 = new JLabel("Placa");
		lblPlaca_1.setBounds(3, 0, 99, 47);
		panel_1.add(lblPlaca_1);
		lblPlaca_1.setForeground(Color.WHITE);
		lblPlaca_1.setFont(new Font("Arial", Font.PLAIN, 40));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(1035, 11, 285, 30);
		panelDiario.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		lblPlaca.setBounds(3, 0, 76, 30);
		panel_2.add(lblPlaca);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		
		textoPlaca = new JTextField();
		textoPlaca.setBounds(121, 0, 166, 30);
		panel_2.add(textoPlaca);
		textoPlaca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textoPlaca.selectAll();
			}
		});
		textoPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		textoPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isCarro = textoPlaca.getText().charAt(textoPlaca.getText().length()-1)>47 && textoPlaca.getText().charAt(textoPlaca.getText().length()-1)<58;
				if (textoPlaca.getText().length()==5) {
					textoCascos.requestFocus();
				}else{
					if(!isCarro){
						textoCascos.requestFocus();
					}else{
						btnRegistrar.requestFocus();
					}
				}
			}
		});
		textoPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		textoPlaca.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(Color.RED);
		panel_3.setBounds(1035, 46, 285, 30);
		panelDiario.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblCascos = new JLabel("Cascos:");
		lblCascos.setForeground(Color.WHITE);
		lblCascos.setBounds(3, 3, 87, 24);
		panel_3.add(lblCascos);
		lblCascos.setFont(new Font("Arial", Font.PLAIN, 20));
		
		textoCascos = new JTextField();
		textoCascos.setBounds(119, 0, 166, 30);
		panel_3.add(textoCascos);
		textoCascos.setFont(new Font("Arial", Font.PLAIN, 20));
		textoCascos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegistrar.requestFocus();
			}
		});
		textoCascos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				textoCascos.selectAll();
			}
		});
		textoCascos.setHorizontalAlignment(SwingConstants.RIGHT);
		textoCascos.setText("0");
		textoCascos.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(InterfazGrafica.class.getResource("/recursos/Logo Parqueadero.jpg")));
		label.setBounds(1035, 504, 285, 121);
		panelDiario.add(label);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBackground(Color.RED);
		panelAdmin.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				String admPass = WinRegistry.leerConfig("", "admPass");
				try {
					if (JOptionPane.showInputDialog("Clave: ").equals(admPass)) {
						getTCierre().setText(String.valueOf(Parqueadero.getHoraCierre()));
						//tCons.setText(String.valueOf(parqueadero.getContabilidad().getConsecutivo())); deprecated
						tCons.setText(WinRegistry.leerConfig("Contabilidad", "consecutivo"));
						actualizarTHistorial();
						eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
						txtAdmMH.setText(WinRegistry.leerConfig("Moto", "porHora"));
						txtAdmUH.setText(WinRegistry.leerConfig("Moto", "porFraccion"));
						txtAdmPH.setText(WinRegistry.leerConfig("Moto", "tiempoFraccion"));
						txtCarroporHora.setText(WinRegistry.leerConfig("Carro", "porHora"));
						txtCarroporFraccion.setText(WinRegistry.leerConfig("Carro", "porFraccion"));
						txtCarrotFraccion.setText(WinRegistry.leerConfig("Carro", "tiempoFraccion"));
						txtConNumLock.setText(WinRegistry.leerConfig("Lockers", "numLock"));
						txtConPref.setText(WinRegistry.leerConfig("Lockers", "preferidos"));
					}else{
						tabbedPane.setSelectedIndex(0);
						JOptionPane.showMessageDialog(null, "Clave Errada.");
					}
				} catch (NullPointerException e1) {
					tabbedPane.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Operacion cancelada.");
				}
			}
		});		
		JPanel panelMensual = new JPanel();
		panelMensual.setBackground(Color.RED);
		panelMensual.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				actualizarTMensual();
			}
		});
		panelMensual.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				actualizarTMensual();
			}
		});
		tabbedPane.addTab("Mensual", null, panelMensual, null);
		panelMensual.setLayout(null);
		
		scrollMensual = new JScrollPane();
		scrollMensual.setBounds(10, 11, 1042, 614);
		panelMensual.add(scrollMensual);
		
		tMensual = new JTable(rowDatatMensual, columnastMensualV);
		scrollMensual.setViewportView(tMensual);
		
		lblMenNombre = new JLabel("Nombre");
		lblMenNombre.setForeground(Color.WHITE);
		lblMenNombre.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenNombre.setBounds(1062, 14, 81, 24);
		panelMensual.add(lblMenNombre);
		
		txtMenNombre = new JTextField();
		txtMenNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMenCedula.requestFocus();
			}
		});
		txtMenNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenNombre.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenNombre.setColumns(10);
		txtMenNombre.setBounds(1153, 11, 166, 30);
		panelMensual.add(txtMenNombre);
		
		lblMenCedula = new JLabel("Cedula");
		lblMenCedula.setForeground(Color.WHITE);
		lblMenCedula.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenCedula.setBounds(1062, 57, 81, 24);
		panelMensual.add(lblMenCedula);
		
		lblMenCelular = new JLabel("Celular");
		lblMenCelular.setForeground(Color.WHITE);
		lblMenCelular.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenCelular.setBounds(1062, 98, 81, 24);
		panelMensual.add(lblMenCelular);
		
		lblMenPlaca = new JLabel("Placa");
		lblMenPlaca.setForeground(Color.WHITE);
		lblMenPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlaca.setBounds(1062, 139, 81, 24);
		panelMensual.add(lblMenPlaca);
		
		lblMenIngreso = new JLabel("Ingreso");
		lblMenIngreso.setForeground(Color.WHITE);
		lblMenIngreso.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenIngreso.setBounds(1062, 180, 81, 24);
		panelMensual.add(lblMenIngreso);
		
		txtMenCedula = new JTextField();
		txtMenCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMenCelular.requestFocus();
			}
		});
		txtMenCedula.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenCedula.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenCedula.setColumns(10);
		txtMenCedula.setBounds(1153, 54, 166, 30);
		panelMensual.add(txtMenCedula);
		
		txtMenCelular = new JTextField();
		txtMenCelular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMenPlaca.requestFocus();
			}
		});
		txtMenCelular.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenCelular.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenCelular.setColumns(10);
		txtMenCelular.setBounds(1153, 95, 166, 30);
		panelMensual.add(txtMenCelular);
		
		txtMenPlaca = new JTextField();
		txtMenPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMenIngreso.requestFocus();
			}
		});
		txtMenPlaca.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenPlaca.setColumns(10);
		txtMenPlaca.setBounds(1153, 136, 166, 30);
		panelMensual.add(txtMenPlaca);
		
		txtMenIngreso = new JTextField();
		txtMenIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMenIngresar.requestFocus();
			}
		});
		txtMenIngreso.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenIngreso.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenIngreso.setColumns(10);
		txtMenIngreso.setBounds(1153, 177, 166, 30);
		panelMensual.add(txtMenIngreso);
		
		btnMenIngresar = new JButton("Ingresar");
		btnMenIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarMensual();
			}
		});
		btnMenIngresar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMenIngresar.setBounds(1062, 297, 257, 40);
		panelMensual.add(btnMenIngresar);
		
		txtMenPlacaPago = new JTextField();
		txtMenPlacaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPagoMensual.requestFocus();
			}
		});
		txtMenPlacaPago.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenPlacaPago.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenPlacaPago.setColumns(10);
		txtMenPlacaPago.setBounds(1153, 362, 166, 30);
		panelMensual.add(txtMenPlacaPago);
		
		lblMenPlacaPago = new JLabel("Placa");
		lblMenPlacaPago.setForeground(Color.WHITE);
		lblMenPlacaPago.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlacaPago.setBounds(1062, 365, 81, 24);
		panelMensual.add(lblMenPlacaPago);
		
		btnPagoMensual = new JButton("Pago");
		btnPagoMensual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagarMensual();
			}
		});
		btnPagoMensual.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPagoMensual.setBounds(1062, 403, 257, 40);
		panelMensual.add(btnPagoMensual);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.WHITE);
		separator_1.setForeground(Color.WHITE);
		separator_1.setBounds(1062, 348, 257, 14);
		panelMensual.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.WHITE);
		separator_2.setBackground(Color.WHITE);
		separator_2.setBounds(1062, 528, 257, 8);
		panelMensual.add(separator_2);
		
		JLabel lblMenPlacaRetirar = new JLabel("Placa");
		lblMenPlacaRetirar.setForeground(Color.WHITE);
		lblMenPlacaRetirar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlacaRetirar.setBounds(1062, 547, 81, 24);
		panelMensual.add(lblMenPlacaRetirar);
		
		txtMenRetirar = new JTextField();
		txtMenRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnMenRetirar.requestFocus();
			}
		});
		txtMenRetirar.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenRetirar.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenRetirar.setColumns(10);
		txtMenRetirar.setBounds(1153, 544, 166, 30);
		panelMensual.add(txtMenRetirar);
		
		btnMenRetirar = new JButton("Retirar");
		btnMenRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retirarMensual(txtMenRetirar.getText().trim().toUpperCase());
			}
		});
		btnMenRetirar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMenRetirar.setBounds(1062, 585, 257, 40);
		panelMensual.add(btnMenRetirar);
		
		txtMenMensualidadIn = new JTextField();
		txtMenMensualidadIn.setHorizontalAlignment(SwingConstants.LEFT);
		txtMenMensualidadIn.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenMensualidadIn.setColumns(10);
		txtMenMensualidadIn.setBounds(1190, 256, 129, 30);
		panelMensual.add(txtMenMensualidadIn);
		
		JLabel lblMenMensualidad = new JLabel("Mensualidad");
		lblMenMensualidad.setForeground(Color.WHITE);
		lblMenMensualidad.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenMensualidad.setBounds(1062, 259, 123, 24);
		panelMensual.add(lblMenMensualidad);
		
		JLabel lblMenTipo = new JLabel("Tipo");
		lblMenTipo.setForeground(Color.WHITE);
		lblMenTipo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenTipo.setBounds(1062, 218, 81, 24);
		panelMensual.add(lblMenTipo);
		
		comboMenTipo = new JComboBox();
		comboMenTipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				txtMenMensualidadIn.setText(WinRegistry.leerConfig("ContabilidadMes", "mes"+(String)comboMenTipo.getSelectedItem()));
			}
		});
		comboMenTipo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comboMenTipo.setBounds(1153, 218, 166, 30);
		comboMenTipo.addItem("Moto");
		comboMenTipo.addItem("Carro");
		panelMensual.add(comboMenTipo);
		tabbedPane.addTab("Administracion", null, panelAdmin, null);
		panelAdmin.setLayout(null);
		
		JLabel lblHoraDeCierre = new JLabel("H. Cierre");
		lblHoraDeCierre.setForeground(Color.WHITE);
		lblHoraDeCierre.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHoraDeCierre.setBounds(1118, 545, 87, 24);
		panelAdmin.add(lblHoraDeCierre);
		
		tCierre = new JTextField();
		tCierre.setFont(new Font("Arial", Font.PLAIN, 20));
		tCierre.setHorizontalAlignment(SwingConstants.RIGHT);
		tCierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comando = tCierre.getText().trim();
				switch(comando){
				case "resetContabilidad":
					parqueadero.setContabilidad(new Contabilidad());
					JOptionPane.showMessageDialog(null, "Contabilidad reiniciada.");
					tCierre.setText(Parqueadero.getHoraCierre());
					break;
				case "listLock":
					JOptionPane.showMessageDialog(null, parqueadero.getLockers().toString());
					break;
				default:
					parqueadero.setHoraCierre(getTCierre().getText().trim());
					JOptionPane.showMessageDialog(null, "Hora de cierre cambiada correctamente.");
					parqueadero.guardar();
					break;
				}
			}
		});
		tCierre.setBounds(1204, 542, 121, 30);
		panelAdmin.add(tCierre);
		tCierre.setColumns(10);
		
		tCons = new JTextField();
		tCons.setFont(new Font("Arial", Font.PLAIN, 20));
		tCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Contabilidad", "consecutivo", tCons.getText().trim());
				JOptionPane.showMessageDialog(null, "Se ha registrado el consecutivo correctamente.");
				parqueadero.guardar();
			}
		});
		tCons.setHorizontalAlignment(SwingConstants.RIGHT);
		tCons.setBounds(1170, 580, 155, 30);
		panelAdmin.add(tCons);
		tCons.setColumns(10);
		
		lblConsecutivo = new JLabel("Cons");
		lblConsecutivo.setForeground(Color.WHITE);
		lblConsecutivo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblConsecutivo.setBounds(1118, 583, 106, 24);
		panelAdmin.add(lblConsecutivo);
		
		JLabel lblBan = new JLabel("Ban");
		lblBan.setForeground(Color.WHITE);
		lblBan.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBan.setBounds(1118, 469, 67, 24);
		panelAdmin.add(lblBan);
		
		txtBan = new JTextField();
		txtBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parqueadero.banear(txtBan.getText().trim());
				JOptionPane.showMessageDialog(null, txtBan.getText().trim()+" ha sido baneado.");
				txtBan.setText("");
				parqueadero.guardar();
			}
		});
		txtBan.setHorizontalAlignment(SwingConstants.RIGHT);
		txtBan.setFont(new Font("Arial", Font.PLAIN, 20));
		txtBan.setColumns(10);
		txtBan.setBounds(1184, 466, 141, 30);
		panelAdmin.add(txtBan);
		
		JLabel lblUnBan = new JLabel("Unban");
		lblUnBan.setForeground(Color.WHITE);
		lblUnBan.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUnBan.setBounds(1118, 507, 67, 24);
		panelAdmin.add(lblUnBan);
		
		txtUnBan = new JTextField();
		txtUnBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parqueadero.unban(txtUnBan.getText().trim());
				JOptionPane.showMessageDialog(null, txtUnBan.getText().trim()+" ha sido habilitado.");
				txtUnBan.setText("");
				parqueadero.guardar();
			}
		});
		txtUnBan.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUnBan.setFont(new Font("Arial", Font.PLAIN, 20));
		txtUnBan.setColumns(10);
		txtUnBan.setBounds(1184, 504, 141, 30);
		panelAdmin.add(txtUnBan);
		
		JTabbedPane tabbedPane_Adm = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Adm.setBorder(null);
		tabbedPane_Adm.setBackground(Color.YELLOW);
		tabbedPane_Adm.setBounds(10, 11, 1094, 620);
		panelAdmin.add(tabbedPane_Adm);
		
		tabAdmHDiario = new JPanel();
		tabAdmHDiario.setBackground(Color.RED);
		tabbedPane_Adm.addTab("Historial Diario", null, tabAdmHDiario, null);
		tabAdmHDiario.setLayout(null);
		
		JLabel lblHistorialDiario = new JLabel("Historial Diario");
		lblHistorialDiario.setForeground(Color.WHITE);
		lblHistorialDiario.setBounds(10, 11, 127, 24);
		tabAdmHDiario.add(lblHistorialDiario);
		lblHistorialDiario.setFont(new Font("Arial", Font.PLAIN, 20));
		
		scrollHistorial = new JScrollPane();
		scrollHistorial.setBounds(10, 39, 1069, 542);
		tabAdmHDiario.add(scrollHistorial);
		scrollHistorial.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				for(CupoDiario next: parqueadero.getDataBank().getHistorialDiario()){
					Vector fila = new Vector();
					fila.add(next.getCliente().getPlaca());
					if (next.getLockerAsignado() == null) {
						fila.add("Ninguno");
						fila.add("-");
					} else {
						fila.add(next.getLockerAsignado().getIdentificador());
						fila.add(next.getLockerAsignado().getCantidad());
					}
					fila.add(next.getValorCobrado());
					fila.add(next.getTiempoTranscurrido());
					rowDatatHistorial.add(fila);
				}
				tHistorial = new JTable(rowDatatHistorial, columnastHistorialV);
				scrollHistorial.setViewportView(tHistorial);
			}
		});
		
		tHistorial = new JTable(rowDatatHistorial, columnastHistorialV);
		scrollHistorial.setViewportView(tHistorial);
		
		JPanel tabAdmIngreso = new JPanel();
		tabAdmIngreso.setBorder(null);
		tabAdmIngreso.setBackground(Color.RED);
		tabbedPane_Adm.addTab("Ingreso especial", null, tabAdmIngreso, null);
		tabbedPane_Adm.setBackgroundAt(1, Color.YELLOW);
		tabAdmIngreso.setLayout(null);
		
		txtAdmFecha = new JTextField();
		txtAdmFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAdmPlaca.requestFocus();
			}
		});
		txtAdmFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmFecha.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmFecha.setColumns(10);
		txtAdmFecha.setBounds(75, 8, 155, 30);
		tabAdmIngreso.add(txtAdmFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(Color.WHITE);
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFecha.setBounds(10, 11, 69, 24);
		tabAdmIngreso.add(lblFecha);
		
		JLabel lblPlaca_2 = new JLabel("Placa:");
		lblPlaca_2.setForeground(Color.WHITE);
		lblPlaca_2.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPlaca_2.setBounds(10, 62, 69, 24);
		tabAdmIngreso.add(lblPlaca_2);
		
		txtAdmPlaca = new JTextField();
		txtAdmPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAdmIngreso.requestFocus();
			}
		});
		txtAdmPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmPlaca.setColumns(10);
		txtAdmPlaca.setBounds(75, 59, 155, 30);
		tabAdmIngreso.add(txtAdmPlaca);
		
		JLabel lblHoraIngreso = new JLabel("Hora Ingreso:");
		lblHoraIngreso.setForeground(Color.WHITE);
		lblHoraIngreso.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHoraIngreso.setBounds(240, 11, 133, 24);
		tabAdmIngreso.add(lblHoraIngreso);
		
		txtAdmIngreso = new JTextField();
		txtAdmIngreso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAdmSalida.requestFocus();
			}
		});
		txtAdmIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmIngreso.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmIngreso.setColumns(10);
		txtAdmIngreso.setBounds(370, 8, 138, 30);
		tabAdmIngreso.add(txtAdmIngreso);
		
		JLabel lblHoraSalida = new JLabel("Hora Salida:");
		lblHoraSalida.setForeground(Color.WHITE);
		lblHoraSalida.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHoraSalida.setBounds(240, 62, 133, 24);
		tabAdmIngreso.add(lblHoraSalida);
		
		txtAdmSalida = new JTextField();
		txtAdmSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAdmVC.requestFocus();
				txtAdmVC.selectAll();
			}
		});
		txtAdmSalida.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmSalida.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmSalida.setColumns(10);
		txtAdmSalida.setBounds(370, 59, 138, 30);
		tabAdmIngreso.add(txtAdmSalida);
		
		txtAdmVC = new JTextField();
		txtAdmVC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAdmIngresar.requestFocus();
			}
		});
		txtAdmVC.setText("0");
		txtAdmVC.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmVC.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmVC.setColumns(10);
		txtAdmVC.setBounds(659, 8, 127, 30);
		tabAdmIngreso.add(txtAdmVC);
		
		JLabel lblValorCobrado = new JLabel("Valor Cobrado:");
		lblValorCobrado.setForeground(Color.WHITE);
		lblValorCobrado.setFont(new Font("Arial", Font.PLAIN, 20));
		lblValorCobrado.setBounds(518, 11, 143, 24);
		tabAdmIngreso.add(lblValorCobrado);
		
		btnAdmIngresar = new JButton("Ingresar");
		btnAdmIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresoEspecial();
			}
		});
		btnAdmIngresar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdmIngresar.setBounds(850, 7, 229, 32);
		tabAdmIngreso.add(btnAdmIngresar);
		
		btnAdmCerrarDiaEspecial = new JButton("Cerrar Dia Especial");
		btnAdmCerrarDiaEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarDiaEspecial();
			}
		});
		btnAdmCerrarDiaEspecial.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdmCerrarDiaEspecial.setBounds(850, 58, 229, 32);
		tabAdmIngreso.add(btnAdmCerrarDiaEspecial);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.RED);
		tabbedPane_Adm.addTab("Configuraciones", null, panel_5, null);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setForeground(Color.BLACK);
		panel_6.setBackground(Color.RED);
		panel_6.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Lockers", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		panel_6.setBounds(10, 11, 1069, 61);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel lblLockers = new JLabel("# lockers");
		lblLockers.setForeground(Color.WHITE);
		lblLockers.setBounds(10, 27, 53, 14);
		panel_6.add(lblLockers);
		
		txtConNumLock = new JTextField();
		txtConNumLock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Lockers", "numLock", txtConNumLock.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
				List<String> preferidos = new ArrayList<String>(); 
				String preferido = WinRegistry.leerConfig("Lockers", "preferidos");
				String[] preferArray = preferido.split(":");
				for(int i=0; i<preferArray.length; i++){
					preferidos.add(preferArray[i]);
				}
				parqueadero.relistarLockers(Integer.parseInt(WinRegistry.leerConfig("Lockers", "numLock")), preferidos);
			}
		});
		txtConNumLock.setBounds(74, 24, 71, 20);
		panel_6.add(txtConNumLock);
		txtConNumLock.setColumns(10);
		
		JLabel lblPreferidos = new JLabel("Preferidos");
		lblPreferidos.setForeground(Color.WHITE);
		lblPreferidos.setBounds(155, 27, 71, 14);
		panel_6.add(lblPreferidos);
		
		txtConPref = new JTextField();
		txtConPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WinRegistry.guardarConfig("Lockers", "preferidos", txtConPref.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
				List<String> preferidos = new ArrayList<String>(); 
				String preferido = WinRegistry.leerConfig("Lockers", "preferidos");
				String[] preferArray = preferido.split(":");
				for(int i=0; i<preferArray.length; i++){
					preferidos.add(preferArray[i]);
				}
				parqueadero.relistarLockers(Integer.parseInt(WinRegistry.leerConfig("Lockers", "numLock")), preferidos);
			}
		});
		txtConPref.setBounds(238, 24, 819, 20);
		panel_6.add(txtConPref);
		txtConPref.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setForeground(Color.BLACK);
		panel_7.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), "Cascos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_7.setBackground(Color.RED);
		panel_7.setBounds(10, 85, 1069, 61);
		panel_5.add(panel_7);
		
		JLabel lblPrecioPorCasco = new JLabel("Precio por casco:");
		lblPrecioPorCasco.setForeground(Color.WHITE);
		lblPrecioPorCasco.setBounds(10, 27, 120, 14);
		panel_7.add(lblPrecioPorCasco);
		
		txtPrecioCascos = new JTextField();
		txtPrecioCascos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WinRegistry.guardarConfig("Lockers", "precioCasco", String.valueOf(txtPrecioCascos.getText()));
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtPrecioCascos.setColumns(10);
		txtPrecioCascos.setBounds(119, 24, 92, 20);
		panel_7.add(txtPrecioCascos);
		
		lblTotalCobrado = new JLabel("Total Cobrado:");
		lblTotalCobrado.setForeground(Color.WHITE);
		lblTotalCobrado.setBounds(1127, 334, 198, 30);
		panelAdmin.add(lblTotalCobrado);
		lblTotalCobrado.setFont(new Font("Arial", Font.PLAIN, 26));
		
		eTotalCobrado = new JLabel("0");
		eTotalCobrado.setBounds(1133, 375, 192, 30);
		panelAdmin.add(eTotalCobrado);
		eTotalCobrado.setBackground(Color.WHITE);
		eTotalCobrado.setForeground(Color.WHITE);
		eTotalCobrado.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
			}
		});
		eTotalCobrado.setFont(new Font("Arial", Font.PLAIN, 26));
		eTotalCobrado.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnTest = new JButton("Cerrar Dia");
		btnTest.setBounds(1127, 418, 198, 33);
		panelAdmin.add(btnTest);
		btnTest.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(1118, 11, 205, 314);
		panelAdmin.add(tabbedPane_1);
		
		JPanel panel = new JPanel();
		tabbedPane_1.addTab("Motos", null, panel, null);
		panel.setBackground(Color.RED);
		panel.setBorder(null);
		
		JLabel lblAdmMedHora = new JLabel("Por Hora:");
		lblAdmMedHora.setForeground(Color.WHITE);
		lblAdmMedHora.setBounds(12, 13, 176, 24);
		lblAdmMedHora.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtAdmMH = new JTextField();
		txtAdmMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Moto", "porHora", txtAdmMH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmMH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAdmMH.selectAll();
			}
		});
		txtAdmMH.setBounds(12, 50, 176, 30);
		txtAdmMH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmMH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmMH.setColumns(10);
		lblAdmMedHora.setLabelFor(txtAdmMH);
		
		JLabel lblAdmUH = new JLabel("Fraccion:");
		lblAdmUH.setForeground(Color.WHITE);
		lblAdmUH.setBounds(12, 93, 176, 24);
		lblAdmUH.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtAdmUH = new JTextField();
		txtAdmUH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAdmUH.selectAll();
			}
		});
		txtAdmUH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Moto", "porFraccion", txtAdmUH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmUH.setBounds(12, 130, 176, 30);
		txtAdmUH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmUH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmUH.setColumns(10);
		
		JLabel lblAdmPH = new JLabel("t Fraccion(m):");
		lblAdmPH.setForeground(Color.WHITE);
		lblAdmPH.setBounds(12, 173, 176, 24);
		lblAdmPH.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtAdmPH = new JTextField();
		txtAdmPH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAdmPH.selectAll();
			}
		});
		txtAdmPH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Moto", "tiempoFraccion", txtAdmPH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmPH.setBounds(12, 210, 176, 30);
		txtAdmPH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmPH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmPH.setColumns(10);
		panel.setLayout(null);
		panel.add(lblAdmMedHora);
		panel.add(txtAdmMH);
		panel.add(lblAdmUH);
		panel.add(txtAdmUH);
		panel.add(lblAdmPH);
		panel.add(txtAdmPH);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.RED);
		tabbedPane_1.addTab("Carros", null, panel_4, null);
		tabbedPane_1.setBackgroundAt(1, Color.YELLOW);
		panel_4.setLayout(null);
		
		JLabel lblPorHora = new JLabel("Por Hora:");
		lblPorHora.setForeground(Color.WHITE);
		lblPorHora.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPorHora.setBounds(12, 13, 176, 24);
		panel_4.add(lblPorHora);
		
		txtCarroporHora = new JTextField();
		txtCarroporHora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WinRegistry.guardarConfig("Carro", "porHora", txtCarroporHora.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtCarroporHora.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCarroporHora.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCarroporHora.setColumns(10);
		txtCarroporHora.setBounds(12, 50, 176, 30);
		panel_4.add(txtCarroporHora);
		
		JLabel lblFraccion = new JLabel("Fraccion:");
		lblFraccion.setForeground(Color.WHITE);
		lblFraccion.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFraccion.setBounds(12, 93, 176, 24);
		panel_4.add(lblFraccion);
		
		txtCarroporFraccion = new JTextField();
		txtCarroporFraccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Carro", "porFraccion", txtCarroporFraccion.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtCarroporFraccion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCarroporFraccion.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCarroporFraccion.setColumns(10);
		txtCarroporFraccion.setBounds(12, 130, 176, 30);
		panel_4.add(txtCarroporFraccion);
		
		txtCarrotFraccion = new JTextField();
		txtCarrotFraccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Carro", "tiempoFraccion", txtCarrotFraccion.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtCarrotFraccion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtCarrotFraccion.setFont(new Font("Arial", Font.PLAIN, 20));
		txtCarrotFraccion.setColumns(10);
		txtCarrotFraccion.setBounds(12, 211, 176, 30);
		panel_4.add(txtCarrotFraccion);
		
		JLabel lblTiempoFraccionm = new JLabel("t Fraccion(m):");
		lblTiempoFraccionm.setForeground(Color.WHITE);
		lblTiempoFraccionm.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTiempoFraccionm.setBounds(12, 174, 176, 24);
		panel_4.add(lblTiempoFraccionm);
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarDia();
			}
		});
		
		JMenuItem printItem = new JMenuItem("Imprimir");
        printItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CupoDiario cupo = parqueadero.getDataBank().buscarCupoDiario((String)tHistorial.getValueAt(tHistorial.getSelectedRow(), 0));
                PrintNow.imprimirReciboSalida(cupo);
            }
        });
        popupMenu.add(printItem);
        popupMenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = tHistorial.rowAtPoint(SwingUtilities.convertPoint(popupMenu, new Point(0, 0), tHistorial));
                        if (rowAtPoint > -1) {
                        	tHistorial.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {				
			}
		});
        JMenuItem printItemDiario = new JMenuItem("Imprimir");
        printItemDiario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CupoDiario cupo = parqueadero.buscarCupoDiario((String)tableMotosDiario.getValueAt(tableMotosDiario.getSelectedRow(), 1));
                PrintNow.imprimirReciboEntrada(cupo);
                actualizarTMotoDiario();
                textoPlaca.requestFocus();
                
            }
        });
        JMenuItem cancelItemDiario = new JMenuItem("Anular");
        cancelItemDiario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CupoDiario cupo = parqueadero.buscarCupoDiario((String)tableMotosDiario.getValueAt(tableMotosDiario.getSelectedRow(), 1));
                if(parqueadero.anularCupoDiario(cupo)){
                	JOptionPane.showMessageDialog(null, "Item Anulado");
                }
                actualizarTMotoDiario();
                textoPlaca.requestFocus();
            }
        });
        popupMenuDiario.add(printItemDiario);
        popupMenuDiario.add(cancelItemDiario);
        popupMenuDiario.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = tableMotosDiario.rowAtPoint(SwingUtilities.convertPoint(popupMenuDiario, new Point(0, 0), tableMotosDiario));
                        if (rowAtPoint > -1) {
                        	tableMotosDiario.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                        }
                    }
                });
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
		});
		tableMotosDiario = new JTable(rowDataMotosDiario, columnasMotosDiarioV);
	}
	
	protected void cerrarDiaEspecial() {
		String fechaStr = txtAdmFecha.getText().trim();
		try {
			if (JOptionPane.showInputDialog("Ingrese la clave para cerrar.").equals("8051")) {
				List<RegistroDiario> dia = parqueadero.getContabilidad().getDia(fechaStr);
				RegistroDiario first = dia.get(0);
				RegistroDiario last =  dia.get(dia.size()-1);
				double totalDia = 0;
				for(RegistroDiario next: dia){
					totalDia += next.getCupo().getValorCobrado();
				}
				PrintNow.printResumenDia(String.valueOf(first.getConsecutivoAsignado()), String.valueOf(last.getConsecutivoAsignado()), String.valueOf(totalDia));
				CreateExlFile.guardarContabilidad(parqueadero.getContabilidad(),fechaStr);
				eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
				JOptionPane.showMessageDialog(null,
						"El dia " + fechaStr + " se ha cerrado correctamente");
				parqueadero.guardar();
			} else {
				JOptionPane.showMessageDialog(null, "Clave Errada.");
			} 
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Operacion cancelada.");
		}
	}

	protected void ingresoEspecial() {
		String placa = txtAdmPlaca.getText();
		GregorianCalendar ingreso = Utilidades.fechaConstructor(txtAdmFecha.getText().trim(), txtAdmIngreso.getText().trim());
		GregorianCalendar salida = Utilidades.fechaConstructor(txtAdmFecha.getText().trim(), txtAdmSalida.getText().trim());
		GregorianCalendar fecha = ingreso;
		double valor = Double.parseDouble(txtAdmVC.getText().trim());
		if(valor==0){
			parqueadero.ingresoDiarioEspecial(fecha, placa, ingreso, salida);
		}else{
			parqueadero.ingresoDiarioEspecial(fecha, placa, ingreso, salida, valor);
		}
		txtAdmPlaca.setText("");
		txtAdmIngreso.setText("");
		txtAdmSalida.setText("");
		txtAdmVC.setText("0");
		txtAdmPlaca.requestFocus();
	}

	protected void retirarMensual(String placa) {
		if(parqueadero.retirarMensual(placa)){
			JOptionPane.showMessageDialog(null, "Cupo Mensual para: "+placa+" eliminado.");
			actualizarTMensual();
			txtMenRetirar.setText("");
		}
	}

	protected void pagarMensual() {
		CupoMensual cupoAnt = parqueadero.buscarCupoMensual(txtMenPlacaPago.getText().trim().toUpperCase());
		if(cupoAnt!=null){
			String confirmacion = String.format("Esta seguro de facturar con la siguiente informacion?:\nCliente: %s\nPlaca: %s\nMonto: %d\nPeriodo: %s - %s", cupoAnt.getCliente().getNombre(),
					cupoAnt.getCliente().getPlaca(), cupoAnt.getMensualidad(), Utilidades.formaterFecha(cupoAnt.getFechaSiguienteCobro()),
							Utilidades.formaterFecha(Utilidades.mesSiguiente(cupoAnt.getFechaSiguienteCobro())));
			int opcion = JOptionPane.showConfirmDialog(null, confirmacion, "Confirmacion de cobro", JOptionPane.OK_CANCEL_OPTION);
			if(opcion == 0){
				cupoAnt.setFechaSiguienteCobro(Utilidades.mesSiguiente(cupoAnt.getFechaSiguienteCobro()));
				parqueadero.getContabilidadMensual().ingresarCobro(cupoAnt);
				actualizarTMensual();
				txtMenPlacaPago.setText("");
				PrintNow.printReciboMensual(cupoAnt);
				JOptionPane.showMessageDialog(null, "Se ha ingresado el pago correctamente.");
				txtMenPlacaPago.requestFocus();
				parqueadero.guardar();
			}else{
				JOptionPane.showMessageDialog(null, "Cobro cancelado.");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Placa no encontrada.");
			txtMenPlacaPago.setText("");
		}
	}

	 protected void ingresarMensual() {
		if (parqueadero.buscarCupoMensual(txtMenPlaca.getText())==null) {
			parqueadero.ingresarMensual(txtMenNombre.getText().trim().toUpperCase(),
					txtMenCedula.getText().trim().toUpperCase(), txtMenCelular.getText().trim().toUpperCase(),
					txtMenPlaca.getText().trim().toUpperCase(),
					Utilidades.traductorFecha(txtMenIngreso.getText().trim()),(String)comboMenTipo.getSelectedItem(),Integer.parseInt(txtMenMensualidadIn.getText()));
			actualizarTMensual();
			txtMenNombre.setText("");
			txtMenCedula.setText("");
			txtMenCelular.setText("");
			txtMenPlaca.setText("");
			txtMenIngreso.setText("");
			JOptionPane.showMessageDialog(null, "Se ha ingresado el cupo mensual.");
			txtMenNombre.requestFocus();
			parqueadero.guardar();
		}else{
			parqueadero.corregirMensual(txtMenNombre.getText().trim().toUpperCase(), txtMenCedula.getText().trim().toUpperCase(),
					txtMenCelular.getText().trim().toUpperCase(), txtMenPlaca.getText().trim().toUpperCase(),
					Utilidades.traductorFecha(txtMenIngreso.getText()),(String)comboMenTipo.getSelectedItem(),
					Integer.parseInt(txtMenMensualidadIn.getText()));
			actualizarTMensual();
			txtMenNombre.setText("");
			txtMenCedula.setText("");
			txtMenCelular.setText("");
			txtMenPlaca.setText("");
			txtMenIngreso.setText("");
			JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");
			txtMenNombre.requestFocus();
			parqueadero.guardar();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void actualizarTMensual() {
		if (parqueadero.getCuposMensuales()!=null) {
			rowDatatMensual = new Vector();
			for (CupoMensual next : parqueadero.getCuposMensuales()) {
				Vector fila = new Vector();
				fila.add(next.getCliente().getNombre());
				fila.add(next.getCliente().getPlaca());
				fila.add(next.getTipo());
				fila.add(next.getCliente().getCelular());
				fila.add(Utilidades.formaterFecha(next.getFechaSiguienteCobro()));
				fila.add(next.getMensualidad());
				rowDatatMensual.add(fila);
			}
			Collections.reverse(rowDatatMensual);
			tMensual = new JTable(rowDatatMensual, columnastMensualV);
			tMensual.setFont(new Font("Arial", Font.PLAIN, 16));
			tMensual.setRowHeight(25);
			scrollMensual.setViewportView(tMensual);
			tMensual.getSelectionModel().addListSelectionListener(new ListSelectionListener() {				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					selectionMensual();
				}
			});
		}
	}

	protected void selectionMensual() {
		CupoMensual cupo = parqueadero.buscarCupoMensual((String)tMensual.getValueAt(tMensual.getSelectedRow(), 1));
		txtMenNombre.setText(cupo.getCliente().getNombre());
		txtMenCedula.setText(cupo.getCliente().getCedula());
		txtMenCelular.setText(cupo.getCliente().getCelular());
		txtMenPlaca.setText(cupo.getCliente().getPlaca());
		txtMenIngreso.setText(Utilidades.formaterFecha(cupo.getFechaIngreso()));
		comboMenTipo.setSelectedItem(cupo.getTipo());
		txtMenMensualidadIn.setText(String.valueOf(cupo.getMensualidad()));
	}

	protected void cerrarDia() {
		String admPass = WinRegistry.leerConfig("", "admPass");
		try {
			if (JOptionPane.showInputDialog("Ingrese la clave para cerrar.").equals(admPass)) {
				List<RegistroDiario> dia = parqueadero.getContabilidad().getDia(Utilidades.formaterFecha(new GregorianCalendar()));
				if(dia.size()!=0){
					RegistroDiario first = dia.get(0);
					RegistroDiario last = dia.get(dia.size()-1);
					PrintNow.printResumenDia(String.valueOf(first.getConsecutivoAsignado()), String.valueOf(last.getConsecutivoAsignado()), String.valueOf(parqueadero.getContabilidad().getCajaActual()));
				}
				String diaString = Utilidades.formaterFecha(new GregorianCalendar());
				if(parqueadero.getContabilidad().getDia(diaString).size()>0){
					CreateExlFile.guardarContabilidad(parqueadero.getContabilidad(), diaString);
					CreateExlFile.guardarHistorialDia(parqueadero.getDataBank().getHistorialDiario(), diaString);
				}
				if(parqueadero.getContabilidadMensual().getDiaMensuales(diaString).size()>0){
					CreateExlFile.guardarContabilidadMensual(parqueadero.getContabilidadMensual(), diaString);
				}
				CupoDiario.setId(0);
				parqueadero.getDataBank().setHistorialDiario(new ArrayList<CupoDiario>());
				parqueadero.getContabilidad().setCajaActual(0);
				actualizarTHistorial();
				eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
				JOptionPane.showMessageDialog(null,
						"El dia " + Utilidades.formaterFecha(new GregorianCalendar()) + " se ha cerrado correctamente");
				parqueadero.guardar();
			} else {
				JOptionPane.showMessageDialog(null, "Clave Errada.");
			} 
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Operacion cancelada.");
		}
	}

	protected void cobrar() {
		if(parqueadero.getDataBank().getHistorialDiario().size()>0){
			try {
				int valor = (int) Double.parseDouble(textoValor.getText().trim());
				CupoDiario cupo = parqueadero.getDataBank().getHistorialDiario()
						.get(parqueadero.getDataBank().getHistorialDiario().size() - 1);
				if (valor!=0) {				
					cupo.setValorCobrado(Double.parseDouble(textoValor.getText()));
					parqueadero.getContabilidad().ingreso(new GregorianCalendar(),cupo); //Ingreso al sistema de contabilidad
					cupo.getCliente()
							.setMinutosReg((long) (cupo.getCliente().getMinutosReg() + cupo.getTiempoTranscurrido()));
					cupo.getCliente().setCobroTotal((int) (cupo.getCliente().getCobroTotal() + valor));
					ePlaca.setText("");
					eTiempo.setText("Tiempo transcurrido");
					textoValor.setText("0");
					textoPlaca.setText("");
					textoPlaca.requestFocus();
				}else{
					cupo.setValorCobrado(0);
					JOptionPane.showMessageDialog(null, "La orden ha sido anulada.");
					ePlaca.setText("");
					eTiempo.setText("Tiempo transcurrido");
					textoValor.setText("0");
					textoPlaca.setText("");
					textoPlaca.requestFocus();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Formato invalido de valor.");
				CupoDiario cupo = parqueadero.getDataBank().getHistorialDiario()
						.get(parqueadero.getDataBank().getHistorialDiario().size() - 1);
				textoValor.setText(String.valueOf(cupo.getValorAsignado()));
				textoValor.requestFocus();
			} catch (HeadlessException e) {
				e.printStackTrace();
			}			
		}
		//parqueadero.guardar();
	}
	
	protected void printCobrar() {
		if(parqueadero.getDataBank().getHistorialDiario().size()>0){
			int valor = (int) Double.parseDouble(textoValor.getText().trim());
			CupoDiario cupo = parqueadero.getDataBank().getHistorialDiario().get(parqueadero.getDataBank().getHistorialDiario().size()-1);
			cupo.setValorCobrado(Double.parseDouble(textoValor.getText()));
			parqueadero.getContabilidad().ingreso(new GregorianCalendar(),cupo);
			cupo.getCliente().setMinutosReg((long) (cupo.getCliente().getMinutosReg() + cupo.getTiempoTranscurrido()));
			cupo.getCliente().setCobroTotal((int) (cupo.getCliente().getCobroTotal() + valor));
			PrintNow.imprimirReciboSalida(cupo);
			ePlaca.setText("");
			eTiempo.setText("Tiempo transcurrido");
			textoValor.setText("0");
			textoPlaca.setText("");
			textoPlaca.requestFocus();
		}
		//parqueadero.guardar();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void actualizarTHistorial() {
		rowDatatHistorial = new Vector();
		for(CupoDiario next: parqueadero.getDataBank().getHistorialDiario()){
			Vector fila = new Vector();
			fila.add(next.getCliente().getPlaca());
			if (next.getLockerAsignado() == null) {
				fila.add("Ninguno");
				fila.add("-");
			} else {
				fila.add(next.getLockerAsignado().getIdentificador());
				fila.add(next.getLockerAsignado().getCantidad());
			}
			fila.add(next.getValorCobrado());
			fila.add(next.getTiempoTranscurrido());
			fila.add(Utilidades.formaterHora(next.getHoraIngreso()));
			fila.add(Utilidades.formaterHora(next.getHoraSalida()));
			rowDatatHistorial.add(fila);
		}
		Collections.reverse(rowDatatHistorial);
		tHistorial = new JTable(rowDatatHistorial, columnastHistorialV);
		tHistorial.setFont(new Font("Arial", Font.PLAIN, 16));
		tHistorial.setRowHeight(25);
		scrollHistorial.setViewportView(tHistorial);
		tHistorial.setComponentPopupMenu(popupMenu);
	}
	
	private boolean isBanned(String placa){
		for(String next: parqueadero.getBaneados()){
			if(placa.equals(next)){
				return true;
			}
		}
		return false;
	}

	protected void ingresarVehiculo(String input){
		char tipo = 0;
		if(input.length()<6){
			tipo='M';
		}else{
			if(input.charAt(input.length()-1)>64 && input.charAt(input.length()-1)<91 && input.length()<=6){
				tipo='M';
			}else{
				if(input.length()==6){
					tipo='C';
				}else{
					int last = input.length()-1;
					tipo = input.charAt(input.length()-1);
					input = input.substring(0, last);
				}
			}
		}
		if (!isBanned(input)) {
			String placa = input;
			try {
				int cascos = Integer.parseInt(textoCascos.getText().trim());
				if (!placa.equals("")) {
					CupoDiario cupo = parqueadero.ingresarDiario(placa, cascos, tipo);
					if (cupo!=null) {
						PrintNow.imprimirReciboEntrada(cupo);									
						actualizarTMotoDiario();
					}else{
						JOptionPane.showMessageDialog(null, "Tipo de vehiculo no reconocido.");
					}
					textoPlaca.setText("");
					textoCascos.setText("0");
					textoPlaca.requestFocus();
				} else {
					JOptionPane.showMessageDialog(null, "No puede dejar la placa vacia.");
					textoPlaca.requestFocus();
					textoPlaca.selectAll();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Formato invalido de cascos.");
				textoCascos.requestFocus();
				textoCascos.selectAll();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Esta placa esta prohibida en el negocio.");
			textoPlaca.setText("");
			textoCascos.setText("0");
			textoPlaca.requestFocus();
		}
	}
	
	protected void procesoPrincipal() {
		if (!textoPlaca.getText().startsWith("!")) {
			if (textoPlaca.getText().length() >= 5) {
				ingresarVehiculo(textoPlaca.getText().toUpperCase().trim());		//Ingresa el vehiculo segun el tipo.
			} else {
				try {
					CupoDiario cupo = parqueadero.retirarDiario(Integer.parseInt(textoPlaca.getText().toUpperCase().trim()));
					if (cupo != null) {
						textoValor.setText(String.valueOf(cupo.getValorCobrado()));
						actualizarTMotoDiario();
						ePlaca.setText(cupo.getCliente().getPlaca());
						if (cupo.getTiempoTranscurrido() < 60) {
							eTiempo.setText(String.valueOf(cupo.getTiempoTranscurrido()) + " minuto(s).");
						} else {
							int minutos = (int) (cupo.getTiempoTranscurrido() % 60);
							int horas = (int) (cupo.getTiempoTranscurrido() / 60);
							eTiempo.setText(horas + " hora(s) y " + minutos + " minuto(s).");
						}
						textoValor.requestFocus();
					} else {
						JOptionPane.showMessageDialog(null, "El recibo no existe o ha sido retirado.");
						textoPlaca.setText("");
						textoPlaca.requestFocus();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "No puede dejar vacia la casilla de placa.");
					textoPlaca.setText("");
					textoPlaca.requestFocus();
				} catch (HeadlessException e) {
					e.printStackTrace();
				}
			}
		}else{
			if (!getTextoPlaca().getText().equals("")) {
				CupoDiario ress = parqueadero.resucitar(getTextoPlaca().getText().substring(1).toUpperCase().trim());
				if(ress!=null){
					actualizarTMotoDiario();
					JOptionPane.showMessageDialog(null, "Se ha resucitado el recibo: " + ress.getSerial());
					textoPlaca.setText("");
					textoPlaca.requestFocus();
				}else{
					JOptionPane.showMessageDialog(null, "No se pudo resucitar el recibo.");
					textoPlaca.setText("");
					textoPlaca.requestFocus();
				}
			}else{
				JOptionPane.showMessageDialog(null, "No puede dejar vacia la casilla de placa.");
				textoPlaca.setText("");
				textoPlaca.requestFocus();
			}
		}
		//parqueadero.guardar();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void actualizarTMotoDiario() {
		if (parqueadero.getCuposDiarios()!=null) {
			rowDataMotosDiario = new Vector();
			for (CupoDiario next : parqueadero.getCuposDiarios()) {
				Vector fila = new Vector();
				fila.add(next.getSerial());
				fila.add(next.getCliente().getPlaca());
				fila.add(next.getTipo());
				if (next.getLockerAsignado() == null) {
					fila.add("Ninguno");
					fila.add("-");
				} else {
					fila.add(next.getLockerAsignado().getIdentificador());
					fila.add(next.getLockerAsignado().getCantidad());
				}
				fila.add(Utilidades.formaterHora(next.getHoraIngreso()));
				fila.add(String.valueOf(next.getCliente().getEntradas()));
				rowDataMotosDiario.add(fila);
			}
			Collections.reverse(rowDataMotosDiario);
			tableMotosDiario = new JTable(rowDataMotosDiario, columnasMotosDiarioV);
			tableMotosDiario.setFont(new Font("Arial", Font.PLAIN, 16));
			tableMotosDiario.setRowHeight(25);
			scrollPaneDiario.setViewportView(getTableMotosDiario());
			tableMotosDiario.setComponentPopupMenu(popupMenuDiario);
		}
	}

	public JScrollPane getScrollPane() {
		return scrollPaneDiario;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JTable getTableMotosDiario() {
		if(tableMotosDiario==null){
			rowDataMotosDiario = new Vector();
			columnasMotosDiarioV = new Vector(Arrays.asList(columnastableMotosDiarioS));
		}
		return tableMotosDiario;
	}
	public JTextField getTextoPlaca() {
		return textoPlaca;
	}
	public JScrollPane getScrollHistorial() {
		return scrollHistorial;
	}
	public JLabel getETiempo() {
		return eTiempo;
	}
	public JLabel getEPlaca() {
		return ePlaca;
	}
	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}
	public JButton getBtnCobrar() {
		return btnCobrar;
	}
	public JLabel getETotalCobrado() {
		return eTotalCobrado;
	}
	public JTextField getTCierre() {
		return tCierre;
	}
	public JTextField getTCons() {
		return tCons;
	}
	public JScrollPane getScrollMensual() {
		return scrollMensual;
	}
	public JButton getBtnMenRetirar() {
		return btnMenRetirar;
	}
	public JButton getBtnAdmIngresar() {
		return btnAdmIngresar;
	}
	public JTextField getTxtCarroporHora() {
		return txtCarroporHora;
	}
	public JTextField getTxtCarroporFraccion() {
		return txtCarroporFraccion;
	}
	public JTextField getTxtCarrotFraccion() {
		return txtCarrotFraccion;
	}
	public JTextField getTxtConNumLock() {
		return txtConNumLock;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getComboMenTipo() {
		return comboMenTipo;
	}
	public JTextField getTxtMenMensualidadIn() {
		return txtMenMensualidadIn;
	}
}
