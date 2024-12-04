package com.example.hotel.controller;

import com.example.hotel.vista.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class OcupationStatisticsController {

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> nombresHabitaciones = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        String[] tipos={"Doble", "Doble Individual", "Junior Suite", "Suite"};
        nombresHabitaciones.addAll(tipos);

        xAxis.setCategories(nombresHabitaciones);
    }

    public void setReservaData(ObservableList<Reserva> reservas) {
        int[] typeCounter = new int[nombresHabitaciones.size()];

        barChart.getData().clear();

        for (Reserva r : reservas) {
            switch (r.getTipoHabitacion().toLowerCase()) {
                case "doble":
                    typeCounter[0]++;
                    break;
                case "doble individual":
                    typeCounter[1]++;
                    break;
                case "junior suite":
                    typeCounter[2]++;
                    break;
                case "suite":
                    typeCounter[3]++;
                    break;
            }
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Reservas por Tipo de Habitación");

        for (int i = 0; i < typeCounter.length; i++) {
            series.getData().add(new XYChart.Data<>(nombresHabitaciones.get(i), typeCounter[i]));
        }

        barChart.getData().add(series);
    }


}
