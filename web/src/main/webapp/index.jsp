<!DOCTYPE html>
<html lang="en">
<!-- [Head] start -->

<head>
    <title>Chart Widget | Able Pro Dashboard Template</title>
    <!-- [Meta] -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- [Favicon] icon -->
    <link rel="icon" href="./assets/images/favicon.svg" type="image/x-icon"> <!-- [Font] Family -->
    <link rel="stylesheet" href="./assets/fonts/inter/inter.css" id="main-font-link" />
    <!-- [Tabler Icons] https://tablericons.com -->
    <link rel="stylesheet" href="./assets/fonts/tabler-icons.min.css" >
    <!-- [Feather Icons] https://feathericons.com -->
    <link rel="stylesheet" href="./assets/fonts/feather.css" >
    <!-- [Font Awesome Icons] https://fontawesome.com/icons -->
    <link rel="stylesheet" href="./assets/fonts/fontawesome.css" >
    <!-- [Material Icons] https://fonts.google.com/icons -->
    <link rel="stylesheet" href="./assets/fonts/material.css" >
    <!-- [Template CSS Files] -->
    <link rel="stylesheet" href="./assets/css/style.css" id="main-style-link" >
    <link rel="stylesheet" href="./assets/css/style-preset.css" >
    <!-- Google tag (gtag.js) -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-14K1GBX9FG"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag() {
            dataLayer.push(arguments);
        }
        gtag('js', new Date());

        gtag('config', 'G-14K1GBX9FG');
    </script>
    <!-- WiserNotify -->
    <script>
        !(function () {
            if (window.t4hto4) console.log('WiserNotify pixel installed multiple time in this page');
            else {
                window.t4hto4 = !0;
                var t = document,
                    e = window,
                    n = function () {
                        var e = t.createElement('script');
                        (e.type = 'text/javascript'),
                            (e.async = !0),
                            (e.src = 'https://pt.wisernotify.com/pixel.js?ti=1jclj6jkfc4hhry'),
                            document.body.appendChild(e);
                    };
                'complete' === t.readyState ? n() : window.attachEvent ? e.attachEvent('onload', n) : e.addEventListener('load', n, !1);
            }
        })();
    </script>
    <!-- Microsoft clarity -->
    <script type="text/javascript">
        (function (c, l, a, r, i, t, y) {
            c[a] =
                c[a] ||
                function () {
                    (c[a].q = c[a].q || []).push(arguments);
                };
            t = l.createElement(r);
            t.async = 1;
            t.src = 'https://www.clarity.ms/tag/' + i;
            y = l.getElementsByTagName(r)[0];
            y.parentNode.insertBefore(t, y);
        })(window, document, 'clarity', 'script', 'gkn6wuhrtb');
    </script>

</head>

<body data-pc-preset="preset-1" data-pc-sidebar-caption="true" data-pc-direction="ltr" data-pc-theme_contrast="" data-pc-theme="light">
<div class="container-fluid">
    <div class="row">
        <div class="col-4">
            <div class="table-body card-body pt-0">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Live IoT Device Data Update</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center text-muted">
                                    <span class="text-truncate w-100">Vehicle Speed </span>
                                </div>
                            </td>
                            <td class="text-end f-w-600"><span id="vehicle_speed">0</span> kmph</td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center text-muted">
                                    <span class="text-truncate w-100">Traffic Light Status</span>
                                </div>
                            </td>
                            <td class="text-end f-w-600"><span id="traffic_light">NONE</span></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center text-muted">
                                    <span class="text-truncate w-100">GPS Coordinates X</span>
                                </div>
                            </td>
                            <td class="text-end f-w-600"><span id="gps_x">0</span></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center text-muted">
                                    <span class="text-truncate w-100">GPS Coordinates X</span>
                                </div>
                            </td>
                            <td class="text-end f-w-600"><span id="gps_y">0</span></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex align-items-center text-muted">
                                    <span class="text-truncate w-100">Date & Time</span>
                                </div>
                            </td>
                            <td class="text-end f-w-600"><span id="date_time">NONE</span></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="page-header-title">
                <h2 class="mb-0">Traffic Analysis</h2>
            </div>
            <div class="card mt-3">
                <div class="card-body">
                    <div class="d-flex align-items-center justify-content-between">
                        <h5 class="mb-0">Average Vehicle Speed: <span id="avg_speed">0</span> kmph</h5>
                    </div>
                    <div id="customer-rate-graph"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="./assets/js/plugins/popper.min.js"></script>
<script src="./assets/js/plugins/simplebar.min.js"></script>
<script src="./assets/js/plugins/bootstrap.min.js"></script>
<script src="./assets/js/fonts/custom-font.js"></script>
<script src="./assets/js/pcoded.js"></script>
<script src="./assets/js/plugins/feather.min.js"></script>

<script>layout_change('light');</script>

<script>layout_theme_contrast_change('false');</script>

<script>change_box_container('false');</script>

<script>layout_caption_change('true');</script>

<script>layout_rtl_change('false');</script>

<script>preset_change("preset-1");</script>

<script type="text/javascript">
    function getTrafficAnalysisData(){
        fetch('trafficAnalysis',{
            method:"POST",
            headers:{
                'Content-Type':'application/json'
            }
        }).then(response => response.json()).then(data => {
            console.log(data.averageSpeed);
            // console.log(data.trafficFlow);
            // console.log(data.mobilityEfficiency);

            document.getElementById("avg_speed").innerHTML = data.averageSpeed;
        });
    }
    setInterval(getTrafficAnalysisData, 5000);

    function getIoTDeviceData(){
        fetch('trafficAnalysis',{
            method:"OPTIONS",
            headers:{
                'Content-Type':'application/json'
            }
        }).then(response => response.json()).then(data => {
            console.log(data.vehicleSpeed);
            // console.log(data.trafficFlow);
            // console.log(data.mobilityEfficiency);

            document.getElementById("vehicle_speed").innerHTML = data.vehicleSpeed;
            document.getElementById("traffic_light").innerHTML = data.trafficLightStatus;
            if(data.trafficLightStatus == "RED"){
                document.getElementById("traffic_light").className = "text-danger";
            }else if (data.trafficLightStatus == "YELLOW"){
                document.getElementById("traffic_light").className = "text-warning";
            }else{
                document.getElementById("traffic_light").className = "text-success";
            }
            document.getElementById("gps_x").innerHTML = data.gpsCoordinatesX;
            document.getElementById("gps_y").innerHTML = data.gpsCoordinatesY;
            document.getElementById("date_time").innerHTML = data.date_time;
        });
    }
    setInterval(getIoTDeviceData, 3000);
</script>

<script src="./assets/js/plugins/apexcharts.min.js"></script>
<script src="./assets/js/pages/w-chart.js"></script>
</body>
</html>
