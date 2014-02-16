<head>
    <meta name="layout" content="main">
    <r:require module="routePlannerPage" />
</head>

<body>
<form name="routeForm" action="route">
    <label for="addresses">Copy and Paste the Addresses below:</label>
    <div>
        <textarea id="addresses" name="addresses" style="width: 50%; height: 500px"></textarea>
    </div>
    <button id="routeCalculatorButton" class="btn btn-primary">Calculate Route</button>
</form>

%{--<span>${data}</span>--}%

</body>
