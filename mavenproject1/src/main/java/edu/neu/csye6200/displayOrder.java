package edu.neu.csye6200;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class displayOrder extends javax.swing.JFrame {

    public displayOrder() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"Date", "Order ID", "Customer ID", "Medicine ID", "Name", "Price"}
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Display Order");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Back to Main Menu");
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
                                .addContainerGap(130, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton2))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        fetchAndDisplayOrders();
    }

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
        form mainForm = new form();
        mainForm.setVisible(true);
        this.dispose();
    }

    private void fetchAndDisplayOrders() {
        String apiUrl = "http://localhost:9999/orders";
        String userId = Session.getInstance().getUserId();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("userHashId", userId);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();
            conn.disconnect();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray orders = jsonResponse.getJSONArray("data");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing rows

            for (int i = 0; i < orders.length(); i++) {
                JSONObject order = orders.getJSONObject(i);
                String orderId = order.getString("id");
                JSONArray customers = order.getJSONArray("customers");
                JSONArray medicines = order.getJSONArray("medicines");

                String customerId = customers.getJSONObject(0).getString("id");
                for (int j = 0; j < medicines.length(); j++) {
                    JSONObject medicine = medicines.getJSONObject(j);
                    String createdDate = medicine.getString("createdDate");
                    String medicineId = medicine.getString("id");
                    String medicineName = medicine.getString("name");
                    int price = medicine.getInt("price");

                    model.addRow(new Object[]{
                            createdDate, // Placeholder for Date
                            orderId,
                            customerId,
                            medicineId,
                            medicineName,
                            price
                    });
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new displayOrder().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
