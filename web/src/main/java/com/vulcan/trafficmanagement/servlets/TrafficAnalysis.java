package com.vulcan.trafficmanagement.servlets;

import com.google.gson.JsonObject;
import com.vulcan.trafficmanagement.model.IoTDevice;
import com.vulcan.trafficmanagement.remote.AnalyticalServer;
import com.vulcan.trafficmanagement.remote.DataStorage;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TrafficAnalysis", value = "/trafficAnalysis")
public class TrafficAnalysis extends HttpServlet {
    private double northLaneSpeedtotal = 0;
    private int northVehicleCount = 0;
    private double southLaneSpeedtotal = 0;
    private int southVehicleCount = 0;
    private double eastLaneSpeedtotal = 0;
    private int eastVehicleCount = 0;
    private double westLaneSpeedtotal = 0;
    private int westVehicleCount = 0;
    @EJB
    AnalyticalServer analyticalServer;
    @EJB
    DataStorage dataStorage;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<com.vulcan.trafficmanagement.model.IoTDevice> deviceList = dataStorage.retriveData();

        for (com.vulcan.trafficmanagement.model.IoTDevice ioTDevice : deviceList) {
            String lane = analyticalServer.decideLane(ioTDevice.getLatitude(), ioTDevice.getLongitude());
            if(lane.equals("NORTH")){
                northLaneSpeedtotal += ioTDevice.getVehicleSpeed();
                northVehicleCount++;
            }else if(lane.equals("SOUTH")){
                southLaneSpeedtotal += ioTDevice.getVehicleSpeed();
                southVehicleCount++;
            }else if(lane.equals("EAST")){
                eastLaneSpeedtotal += ioTDevice.getVehicleSpeed();
                eastVehicleCount++;
            }else if(lane.equals("WEST")){
                westLaneSpeedtotal += ioTDevice.getVehicleSpeed();
                westVehicleCount++;
            }
        }

        double northAverageSpeed = analyticalServer.calculateAverageSpeed(northVehicleCount,northLaneSpeedtotal);
        String northTrafficFlow = analyticalServer.trafficFlowAnalysis(northAverageSpeed);
        double northUME = analyticalServer.calculateUrbanMobilityEfficiency(northAverageSpeed);

        double southAverageSpeed = analyticalServer.calculateAverageSpeed(southVehicleCount,southLaneSpeedtotal);
        String southTrafficFlow = analyticalServer.trafficFlowAnalysis(southAverageSpeed);
        double southUME = analyticalServer.calculateUrbanMobilityEfficiency(southAverageSpeed);

        double eastAverageSpeed = analyticalServer.calculateAverageSpeed(eastVehicleCount,eastLaneSpeedtotal);
        String eastTrafficFlow = analyticalServer.trafficFlowAnalysis(eastAverageSpeed);
        double eastUME = analyticalServer.calculateUrbanMobilityEfficiency(eastAverageSpeed);

        double westAverageSpeed = analyticalServer.calculateAverageSpeed(westVehicleCount,westLaneSpeedtotal);
        String westTrafficFlow = analyticalServer.trafficFlowAnalysis(westAverageSpeed);
        double westUME = analyticalServer.calculateUrbanMobilityEfficiency(westAverageSpeed);

        JsonObject json = new JsonObject();
        json.addProperty("northAverageSpeed", northAverageSpeed);
        json.addProperty("northTrafficFlow", northTrafficFlow);
        json.addProperty("northUME", northUME);

        json.addProperty("southAverageSpeed", southAverageSpeed);
        json.addProperty("southTrafficFlow", southTrafficFlow);
        json.addProperty("southUME", southUME);

        json.addProperty("eastAverageSpeed", eastAverageSpeed);
        json.addProperty("eastTrafficFlow", eastTrafficFlow);
        json.addProperty("eastUME", eastUME);

        json.addProperty("westAverageSpeed", westAverageSpeed);
        json.addProperty("westTrafficFlow", westTrafficFlow);
        json.addProperty("westUME", westUME);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double vehicleSpeed = 0.00;
        String trafficLightStatus = "NONE";
        double longitude = 0.00;
        double latitude = 0.00;
        String date_time = "NONE";

        List<com.vulcan.trafficmanagement.model.IoTDevice> deviceList = dataStorage.retriveData();
        if(deviceList.size() != 0){
            IoTDevice ioTDevice = deviceList.get(deviceList.size() - 1);
            vehicleSpeed = ioTDevice.getVehicleSpeed();
            trafficLightStatus = ioTDevice.getTrafficLightStatus();
            longitude = ioTDevice.getLongitude();
            latitude = ioTDevice.getLatitude();
            date_time = ioTDevice.getDate_time();
        }

        JsonObject json = new JsonObject();
        json.addProperty("vehicleSpeed", vehicleSpeed);
        json.addProperty("trafficLightStatus", trafficLightStatus);
        json.addProperty("longitude", longitude);
        json.addProperty("latitude", latitude);
        json.addProperty("date_time", date_time);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }
}
