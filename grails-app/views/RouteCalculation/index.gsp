<head>
    <meta name="layout" content="main">
    <r:require module="routePlannerPage" />
</head>

<body>

<div class="alert alert-danger ${visible}">
    ${errorMessage}
</div>

<div class="text-center">
    <h2>Route Planner</h2>
    <form name="routeForm" action="route">
        <label for="addresses">Copy and paste the addresses of your destinations below:</label>
        <div>
            <textarea id="addresses" name="addresses" style="width: 50%; height: 450px"></textarea>
        </div>
        <button id="routeCalculatorButton" class="btn btn-primary" style="margin-top: 15px">Calculate Route</button>
    </form>
</div>

</body>
