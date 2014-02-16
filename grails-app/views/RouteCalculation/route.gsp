<head>
    <meta name="layout" content="main">
</head>
<body>
    <div class="text-center">
        <h2>Your route organized for efficiency!</h2>
        <g:each var="location" in="${locations}">
            <div>${location.address}</div>
        </g:each>
    </div>
    <h3>Go <a href="<g:createLink controller='routeCalculation' action='index' />">back</a> to create another route.</h3>
</body>