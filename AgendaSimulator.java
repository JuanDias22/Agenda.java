import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class AgendaSimulator extends JFrame {
    private Map<String, Contato> contatos;
    private DefaultListModel<String> listModel;
    private JList<String> listaContatos;

    public AgendaSimulator() {
        contatos = new HashMap<>();
        listModel = new DefaultListModel<>();
        listaContatos = new JList<>(listModel);

        setTitle("Agenda Simulator");
        setSize(510, 390);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        panel.setBackground(Color.blue);

        JButton adicionarButton = new JButton("Adicionar Contato");
        JButton listarButton = new JButton("Listar Contatos");
        JButton excluirButton = new JButton("Excluir Contato");
        JTextField nomeField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField emailField = new JTextField();

        nomeField.setBorder(BorderFactory.createTitledBorder("Nome do Contato"));
        nomeField.setBackground(Color.white);
        numeroField.setBorder(BorderFactory.createTitledBorder("Número do Contato"));
        numeroField.setBackground(Color.white);
        emailField.setBorder(BorderFactory.createTitledBorder("Email do Contato"));
        emailField.setBackground(Color.white);


        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String numero = numeroField.getText();
                String email = emailField.getText();
                if (!nome.isEmpty() && !numero.isEmpty() && !email.isEmpty()) {
                    Contato contato = new Contato(nome, numero, email);
                    contatos.put(nome, contato);
                    listModel.addElement(nome);
                    nomeField.setText("");
                    numeroField.setText("");
                    emailField.setText("");
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder lista = new StringBuilder("Contatos:\n");
                for (String nome : contatos.keySet()) {
                    Contato contato = contatos.get(nome);
                    lista.append("Nome: ").append(contato.getNome()).append("\n").append("\n");
                    lista.append("Número: ").append(contato.getNumero()).append("\n").append("\n");
                    lista.append("Email: ").append(contato.getEmail()).append("\n\n").append("\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = listaContatos.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String nomeExclusao = listModel.get(selectedIndex);
                    contatos.remove(nomeExclusao);
                    listModel.remove(selectedIndex);
                    JOptionPane.showMessageDialog(null, "Contato excluído: " + nomeExclusao);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um contato para excluir.");
                }
            }
        });

        panel.add(adicionarButton);
        panel.add(listarButton);
        panel.add(excluirButton);
        panel.add(nomeField);
        panel.add(numeroField);
        panel.add(emailField);

        add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        add(panel, BorderLayout.EAST);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AgendaSimulator();
            }
        });
    }

    private class Contato {
        private String nome;
        private String numero;
        private String email;

        public Contato(String nome, String numero, String email) {
            this.nome = nome;
            this.numero = numero;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public String getNumero() {
            return numero;
        }

        public String getEmail() {
            return email;
        }
    }
}
