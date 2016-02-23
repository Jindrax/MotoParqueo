package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;

import negocio.Config;
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
import contabilidad.Transferencia;
import contabilidad.TransferenciaDiaria;
import contabilidad.tipoTrans;
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

@SuppressWarnings("unused")
public class InterfazGrafica {

	private JFrame frmMotoparqueo;
	private static Parqueadero parqueadero = null;
	private JScrollPane scrollPaneDiario;
	private JTable tableMotosDiario;
	private String[] columnastableMotosDiarioS = {"Recibo","Placa","Tipo","Locker","Cascos","Entrada","Cliente"};
	private String[] columnastHistorialS = {"Placa", "Locker", "Cantidad", "Pagado", "Tiempo","Entrada","Salida"};
	private String[] columnastMensualS = {"Nombre","Placa","Celular", "Sig.Cobro"};
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
	private JTextField txtMenMensualidad;
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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Config.inicializar();
					parqueadero = new Parqueadero(Config.getNumeroLockers(), Config.getPreferidos());
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
		WinRegistry.inicializarConfig();
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
				if (parqueadero.getLockers().size()!=Config.getNumeroLockers()) {
					parqueadero.relistarLockers(Config.getNumeroLockers(), Config.getPreferidos());
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
		frmMotoparqueo.setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGrafica.class.getResource("/recursos/IcoMotoParqueo.png")));
		frmMotoparqueo.setTitle("MotoParqueo259");
		frmMotoparqueo.setResizable(false);
		frmMotoparqueo.setBounds(10, 10, 1420, 843);
		frmMotoparqueo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMotoparqueo.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(10, 11, 1394, 799);
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
		panelDiario.setBackground(Color.YELLOW);
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
		scrollPaneDiario.setBounds(10, 11, 1015, 740);
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
		btnRegistrar.setBounds(1259, 87, 120, 40);
		panelDiario.add(btnRegistrar);
		
		JLabel lblValor = new JLabel("Valor");
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
		textoValor.setBounds(1147, 228, 232, 53);
		panelDiario.add(textoValor);
		textoValor.setColumns(10);
		
		eTiempo = new JLabel("Tiempo transcurrido");
		eTiempo.setFont(new Font("Arial", Font.PLAIN, 20));
		eTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		eTiempo.setBounds(1035, 292, 344, 24);
		panelDiario.add(eTiempo);
		
		btnCobrar = new JButton("Cobrar");
		btnCobrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnCobrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cobrar();
			}
		});
		btnCobrar.setBounds(1223, 354, 156, 40);
		panelDiario.add(btnCobrar);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(1035, 150, 344, 5);
		panelDiario.add(separator);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printCobrar();
			}
		});
		btnImprimir.setBounds(1035, 354, 163, 40);
		panelDiario.add(btnImprimir);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(1035, 177, 344, 47);
		panelDiario.add(panel_1);
		panel_1.setLayout(null);
		
		ePlaca = new JLabel("0");
		ePlaca.setBounds(111, 0, 230, 47);
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
		panel_2.setBounds(1035, 11, 344, 30);
		panelDiario.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblPlaca = new JLabel("Placa:");
		lblPlaca.setForeground(Color.WHITE);
		lblPlaca.setBounds(3, 0, 76, 30);
		panel_2.add(lblPlaca);
		lblPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		
		textoPlaca = new JTextField();
		textoPlaca.setBounds(178, 0, 166, 30);
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
				if (textoPlaca.getText().length()>=5) {
					textoCascos.requestFocus();
				}else{
					btnRegistrar.requestFocus();
				}
			}
		});
		textoPlaca.setHorizontalAlignment(SwingConstants.RIGHT);
		textoPlaca.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBackground(Color.YELLOW);
		panel_3.setBounds(1035, 46, 344, 30);
		panelDiario.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblCascos = new JLabel("Cascos:");
		lblCascos.setBounds(3, 3, 87, 24);
		panel_3.add(lblCascos);
		lblCascos.setFont(new Font("Arial", Font.PLAIN, 20));
		
		textoCascos = new JTextField();
		textoCascos.setBounds(178, 0, 166, 30);
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
		label.setIcon(new ImageIcon(InterfazGrafica.class.getResource("/recursos/Logo parqueadero.jpg")));
		label.setBounds(1050, 460, 314, 229);
		panelDiario.add(label);
		
		JPanel panelAdmin = new JPanel();
		panelAdmin.setBackground(Color.YELLOW);
		panelAdmin.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				try {
					if (JOptionPane.showInputDialog("Clave: ").equals("8051")) {
						getTCierre().setText(String.valueOf(Parqueadero.getHoraCierre()));
						tCons.setText(String.valueOf(parqueadero.getContabilidad().getConsecutivo()));
						actualizarTHistorial();
						eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
						txtAdmMH.setText(WinRegistry.leerConfig("Moto", "mediaHora"));
						txtAdmUH.setText(WinRegistry.leerConfig("Moto", "unaHora"));
						txtAdmPH.setText(WinRegistry.leerConfig("Moto", "porHora"));
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
		panelMensual.setBackground(Color.YELLOW);
		panelMensual.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				actualizarTMensual();
				txtMenMensualidad.setText(String.valueOf(parqueadero.getMensualidad()));
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
		scrollMensual.setBounds(10, 11, 1102, 738);
		panelMensual.add(scrollMensual);
		
		tMensual = new JTable(rowDatatMensual, columnastMensualV);
		scrollMensual.setViewportView(tMensual);
		
		lblMenNombre = new JLabel("Nombre");
		lblMenNombre.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenNombre.setBounds(1122, 14, 81, 24);
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
		txtMenNombre.setBounds(1213, 11, 166, 30);
		panelMensual.add(txtMenNombre);
		
		lblMenCedula = new JLabel("Cedula");
		lblMenCedula.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenCedula.setBounds(1122, 57, 81, 24);
		panelMensual.add(lblMenCedula);
		
		lblMenCelular = new JLabel("Celular");
		lblMenCelular.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenCelular.setBounds(1122, 98, 81, 24);
		panelMensual.add(lblMenCelular);
		
		lblMenPlaca = new JLabel("Placa");
		lblMenPlaca.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlaca.setBounds(1122, 139, 81, 24);
		panelMensual.add(lblMenPlaca);
		
		lblMenIngreso = new JLabel("Ingreso");
		lblMenIngreso.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenIngreso.setBounds(1122, 180, 81, 24);
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
		txtMenCedula.setBounds(1213, 54, 166, 30);
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
		txtMenCelular.setBounds(1213, 95, 166, 30);
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
		txtMenPlaca.setBounds(1213, 136, 166, 30);
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
		txtMenIngreso.setBounds(1213, 177, 166, 30);
		panelMensual.add(txtMenIngreso);
		
		btnMenIngresar = new JButton("Ingresar");
		btnMenIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresarMensual();
			}
		});
		btnMenIngresar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMenIngresar.setBounds(1122, 215, 257, 40);
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
		txtMenPlacaPago.setBounds(1213, 311, 166, 30);
		panelMensual.add(txtMenPlacaPago);
		
		lblMenPlacaPago = new JLabel("Placa");
		lblMenPlacaPago.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlacaPago.setBounds(1122, 314, 81, 24);
		panelMensual.add(lblMenPlacaPago);
		
		btnPagoMensual = new JButton("Pago");
		btnPagoMensual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pagarMensual();
			}
		});
		btnPagoMensual.setFont(new Font("Arial", Font.PLAIN, 20));
		btnPagoMensual.setBounds(1122, 352, 257, 40);
		panelMensual.add(btnPagoMensual);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(1122, 286, 257, 14);
		panelMensual.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(1122, 413, 257, 14);
		panelMensual.add(separator_2);
		
		JLabel lblMenPlacaRetirar = new JLabel("Placa");
		lblMenPlacaRetirar.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenPlacaRetirar.setBounds(1122, 435, 81, 24);
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
		txtMenRetirar.setBounds(1213, 432, 166, 30);
		panelMensual.add(txtMenRetirar);
		
		btnMenRetirar = new JButton("Retirar");
		btnMenRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				retirarMensual(txtMenRetirar.getText().trim().toUpperCase());
			}
		});
		btnMenRetirar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnMenRetirar.setBounds(1122, 473, 257, 40);
		panelMensual.add(btnMenRetirar);
		
		JLabel lblMenMen = new JLabel("Mensualidad");
		lblMenMen.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMenMen.setBounds(1122, 722, 123, 24);
		panelMensual.add(lblMenMen);
		
		txtMenMensualidad = new JTextField();
		txtMenMensualidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parqueadero.setMensualidad(Integer.parseInt(txtMenMensualidad.getText().trim()));
				JOptionPane.showMessageDialog(null, "Actualizado el precio de mensualidad.");
				txtMenMensualidad.setText(String.valueOf(parqueadero.getMensualidad()));			
			}
		});
		txtMenMensualidad.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMenMensualidad.setFont(new Font("Arial", Font.PLAIN, 20));
		txtMenMensualidad.setColumns(10);
		txtMenMensualidad.setBounds(1242, 719, 137, 30);
		panelMensual.add(txtMenMensualidad);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(1122, 703, 257, 14);
		panelMensual.add(separator_3);
		tabbedPane.addTab("Administracion", null, panelAdmin, null);
		panelAdmin.setLayout(null);
		
		JLabel lblHoraDeCierre = new JLabel("H. Cierre");
		lblHoraDeCierre.setFont(new Font("Arial", Font.PLAIN, 20));
		lblHoraDeCierre.setBounds(1172, 545, 87, 24);
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
		tCierre.setBounds(1258, 542, 121, 30);
		panelAdmin.add(tCierre);
		tCierre.setColumns(10);
		
		tCons = new JTextField();
		tCons.setFont(new Font("Arial", Font.PLAIN, 20));
		tCons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parqueadero.getContabilidad().setConsecutivo(Long.parseLong(tCons.getText().trim()));
				JOptionPane.showMessageDialog(null, "Se ha registrado el consecutivo correctamente.");
				parqueadero.guardar();
			}
		});
		tCons.setHorizontalAlignment(SwingConstants.RIGHT);
		tCons.setBounds(1224, 580, 155, 30);
		panelAdmin.add(tCons);
		tCons.setColumns(10);
		
		lblConsecutivo = new JLabel("Cons");
		lblConsecutivo.setFont(new Font("Arial", Font.PLAIN, 20));
		lblConsecutivo.setBounds(1172, 583, 106, 24);
		panelAdmin.add(lblConsecutivo);
		
		JLabel lblBan = new JLabel("Ban");
		lblBan.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBan.setBounds(1172, 469, 67, 24);
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
		txtBan.setBounds(1238, 466, 141, 30);
		panelAdmin.add(txtBan);
		
		JLabel lblUnBan = new JLabel("Unban");
		lblUnBan.setFont(new Font("Arial", Font.PLAIN, 20));
		lblUnBan.setBounds(1172, 507, 67, 24);
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
		txtUnBan.setBounds(1238, 504, 141, 30);
		panelAdmin.add(txtUnBan);
		
		JButton btnAdmAbrirCarpeta = new JButton("Abrir Carpeta");
		btnAdmAbrirCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
					Desktop.getDesktop().open(new File(System.getProperty("user.home")+"\\Documents\\Contabilidad-MotoParqueo"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAdmAbrirCarpeta.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdmAbrirCarpeta.setBounds(1172, 716, 207, 33);
		panelAdmin.add(btnAdmAbrirCarpeta);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		panel.setBounds(1172, 46, 207, 412);
		panelAdmin.add(panel);
		
		JLabel lblAdmMedHora = new JLabel("Media Hora:");
		lblAdmMedHora.setBounds(5, 11, 191, 24);
		lblAdmMedHora.setFont(new Font("Arial", Font.PLAIN, 20));
		
		txtAdmMH = new JTextField();
		txtAdmMH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinRegistry.guardarConfig("Moto", "mediaHora", txtAdmMH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmMH.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtAdmMH.selectAll();
			}
		});
		txtAdmMH.setBounds(5, 55, 191, 30);
		txtAdmMH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmMH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmMH.setColumns(10);
		lblAdmMedHora.setLabelFor(txtAdmMH);
		
		JLabel lblAdmUH = new JLabel("Una Hora:");
		lblAdmUH.setBounds(5, 105, 192, 24);
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
				WinRegistry.guardarConfig("Moto", "unaHora", txtAdmUH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmUH.setBounds(5, 149, 191, 30);
		txtAdmUH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmUH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmUH.setColumns(10);
		
		JLabel lblAdmPH = new JLabel("Por Hora:");
		lblAdmPH.setBounds(5, 199, 191, 24);
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
				WinRegistry.guardarConfig("Moto", "porHora", txtAdmPH.getText());
				JOptionPane.showMessageDialog(null, "Valor actualizado.");
			}
		});
		txtAdmPH.setBounds(5, 243, 191, 30);
		txtAdmPH.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAdmPH.setFont(new Font("Arial", Font.PLAIN, 20));
		txtAdmPH.setColumns(10);
		
		lblTotalCobrado = new JLabel("Total Cobrado:");
		lblTotalCobrado.setBounds(5, 284, 198, 30);
		lblTotalCobrado.setFont(new Font("Arial", Font.PLAIN, 26));
		
		eTotalCobrado = new JLabel("0");
		eTotalCobrado.setBounds(5, 325, 192, 30);
		eTotalCobrado.setBackground(Color.WHITE);
		eTotalCobrado.setForeground(Color.BLACK);
		eTotalCobrado.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				eTotalCobrado.setText(String.valueOf(parqueadero.getContabilidad().getCajaActual()));
			}
		});
		eTotalCobrado.setFont(new Font("Arial", Font.PLAIN, 26));
		eTotalCobrado.setHorizontalAlignment(SwingConstants.RIGHT);
		
		btnTest = new JButton("Cerrar Dia");
		btnTest.setBounds(5, 368, 198, 33);
		btnTest.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.setLayout(null);
		panel.add(lblAdmMedHora);
		panel.add(txtAdmMH);
		panel.add(lblAdmUH);
		panel.add(txtAdmUH);
		panel.add(lblAdmPH);
		panel.add(txtAdmPH);
		panel.add(lblTotalCobrado);
		panel.add(eTotalCobrado);
		panel.add(btnTest);
		
		JTabbedPane tabbedPane_Adm = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_Adm.setBorder(null);
		tabbedPane_Adm.setBackground(Color.YELLOW);
		tabbedPane_Adm.setBounds(10, 11, 1152, 738);
		panelAdmin.add(tabbedPane_Adm);
		
		tabAdmHDiario = new JPanel();
		tabAdmHDiario.setBackground(Color.YELLOW);
		tabbedPane_Adm.addTab("Historial Diario", null, tabAdmHDiario, null);
		tabAdmHDiario.setLayout(null);
		
		JLabel lblHistorialDiario = new JLabel("Historial Diario");
		lblHistorialDiario.setBounds(10, 11, 127, 24);
		tabAdmHDiario.add(lblHistorialDiario);
		lblHistorialDiario.setFont(new Font("Arial", Font.PLAIN, 20));
		
		scrollHistorial = new JScrollPane();
		scrollHistorial.setBounds(10, 39, 1127, 660);
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
		tabAdmIngreso.setBackground(Color.YELLOW);
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
		lblFecha.setFont(new Font("Arial", Font.PLAIN, 20));
		lblFecha.setBounds(10, 11, 69, 24);
		tabAdmIngreso.add(lblFecha);
		
		JLabel lblPlaca_2 = new JLabel("Placa:");
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
		btnAdmIngresar.setBounds(850, 7, 268, 32);
		tabAdmIngreso.add(btnAdmIngresar);
		
		btnAdmCerrarDiaEspecial = new JButton("Cerrar Dia Especial");
		btnAdmCerrarDiaEspecial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarDiaEspecial();
			}
		});
		btnAdmCerrarDiaEspecial.setFont(new Font("Arial", Font.PLAIN, 20));
		btnAdmCerrarDiaEspecial.setBounds(850, 58, 268, 32);
		tabAdmIngreso.add(btnAdmCerrarDiaEspecial);
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
				List<Transferencia> dia = parqueadero.getContabilidad().getDia(fechaStr);
				TransferenciaDiaria first = (TransferenciaDiaria) dia.get(0);
				TransferenciaDiaria last = (TransferenciaDiaria) dia.get(dia.size()-1);
				double totalDia = 0;
				for(Transferencia next: dia){
					totalDia += next.getMonto();
				}
				PrintNow.printResumenDia(String.valueOf(first.getConsecutivo()), String.valueOf(last.getConsecutivo()), String.valueOf(totalDia));
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
		GregorianCalendar fechaAnt = (GregorianCalendar) cupoAnt.getFechaSiguienteCobro().clone();
		CupoMensual cupo = parqueadero.pagoMensual(txtMenPlacaPago.getText().trim().toUpperCase());
		parqueadero.getContabilidad().ingreso(new GregorianCalendar(),parqueadero.getMensualidad(), tipoTrans.mensual, cupo.getCliente().getPlaca(), cupo.getCliente().getNombre());
		actualizarTMensual();
		txtMenPlacaPago.setText("");
		PrintNow.printReciboMensual(fechaAnt, cupo, parqueadero.getMensualidad());
		JOptionPane.showMessageDialog(null, "Se ha ingresado el pago correctamente.");
		txtMenPlacaPago.requestFocus();
		parqueadero.guardar();
	}

	 protected void ingresarMensual() {
		if (parqueadero.buscarCupoMensual(txtMenPlaca.getText())==null) {
			parqueadero.ingresarMensual(txtMenNombre.getText().trim().toUpperCase(),
					txtMenCedula.getText().trim().toUpperCase(), txtMenCelular.getText().trim().toUpperCase(),
					txtMenPlaca.getText().trim().toUpperCase(),
					Utilidades.traductorFecha(txtMenIngreso.getText().trim()));
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
			parqueadero.corregirMensual(txtMenNombre.getText().trim().toUpperCase(), txtMenCedula.getText().trim().toUpperCase(), txtMenCelular.getText().trim().toUpperCase(), txtMenPlaca.getText().trim().toUpperCase(), Utilidades.traductorFecha(txtMenIngreso.getText()));
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
				fila.add(next.getCliente().getCelular());
				fila.add(Utilidades.formaterFecha(next.getFechaSiguienteCobro()));
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
	}

	protected void cerrarDia() {
		try {
			if (JOptionPane.showInputDialog("Ingrese la clave para cerrar.").equals("8051")) {
				List<Transferencia> dia = parqueadero.getContabilidad().getDia(Utilidades.formaterFecha(new GregorianCalendar()));
				TransferenciaDiaria first = (TransferenciaDiaria) dia.get(0);
				TransferenciaDiaria last = (TransferenciaDiaria) dia.get(dia.size()-1);
				PrintNow.printResumenDia(String.valueOf(first.getConsecutivo()), String.valueOf(last.getConsecutivo()), String.valueOf(parqueadero.getContabilidad().getCajaActual()));
				CreateExlFile.guardarContabilidad(parqueadero.getContabilidad(), Utilidades.formaterFecha(new GregorianCalendar()));
				CreateExlFile.guardarHistorialDia(parqueadero.getDataBank().getHistorialDiario(), Utilidades.formaterFecha(new GregorianCalendar()));
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
					parqueadero.getContabilidad().ingreso(new GregorianCalendar(),valor, tipoTrans.diario,cupo.getCliente().getPlaca());
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
		parqueadero.guardar();
	}
	
	protected void printCobrar() {
		if(parqueadero.getDataBank().getHistorialDiario().size()>0){
			int valor = (int) Double.parseDouble(textoValor.getText().trim());
			CupoDiario cupo = parqueadero.getDataBank().getHistorialDiario().get(parqueadero.getDataBank().getHistorialDiario().size()-1);
			cupo.setValorCobrado(Double.parseDouble(textoValor.getText()));
			parqueadero.getContabilidad().ingreso(new GregorianCalendar(),valor, tipoTrans.diario,cupo.getCliente().getPlaca());
			cupo.getCliente().setMinutosReg((long) (cupo.getCliente().getMinutosReg() + cupo.getTiempoTranscurrido()));
			cupo.getCliente().setCobroTotal((int) (cupo.getCliente().getCobroTotal() + valor));
			PrintNow.imprimirReciboSalida(cupo);
			ePlaca.setText("");
			eTiempo.setText("Tiempo transcurrido");
			textoValor.setText("0");
			textoPlaca.setText("");
			textoPlaca.requestFocus();
		}
		parqueadero.guardar();
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
		char tipo = input.charAt(0);
		input = input.substring(1, input.length());
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
		parqueadero.guardar();
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
}
