package edu.neu.csye6200;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class displayMedicine extends javax.swing.JFrame {

    /**
     * Creates new form displayMedicine
     */
    public displayMedicine() {
        initComponents();
        addListeners();
    }

    /**
     * Add event listeners to the buttons.
     */
    private void addListeners() {
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayData();
            }
        });
    }

    /**
     * Fetch data from API and display it in the JTable.
     */
    private void fetchAndDisplayData() {
        String apiUrl = "http://localhost:9999/medicines";
        try {
            // Create URL object
            URL url = new URL(apiUrl);
            String userId = Session.getInstance().getUserId();

            // Open HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("userHashId", userId);

            // Read the response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Parse the response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray dataArray = jsonResponse.getJSONArray("data");

            // Prepare table model
            String[] columnNames = {"ID", "Name", "Content", "Price"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);

            // Add rows to the table
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject medicine = dataArray.getJSONObject(i);
                String id = medicine.getString("id");
                String name = medicine.getString("name");
                String content = medicine.getString("content");
                double price = medicine.getDouble("price");

                model.addRow(new Object[]{id, name, content, price});
            }

            // Set model to the table
            jTable1.setModel(model);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to fetch data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Title 1", "Title 2", "Title 3", "Title 4"}
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Display Query");
        jButton2.setText("Back to Menu");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(125, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(jButton1)
                                .addGap(134, 134, 134)
                                .addComponent(jButton2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        // Back to Menu button functionality
        form mainForm = new form();
        mainForm.setVisible(true);
        this.dispose(); // Close the current window
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new displayMedicine().setVisible(true);
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
