<head>
    <meta name="layout" content="main">
</head>
<body>
    <g:each var="location" in="${locations}">
        <div>${location.address}</div>
    </g:each>
</body>