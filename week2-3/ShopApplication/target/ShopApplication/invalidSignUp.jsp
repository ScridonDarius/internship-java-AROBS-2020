<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SignUpInvalid Form</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            color: #fff;
            background: #3598dc;
        }

        .login-form {
            width: 380px;
            margin: 30px auto;
            text-align: center;
        }
        .login-form h2 {
            margin: 40px 0 25px;
        }
        .login-form form {
            color: #7a7a7a;
            border-radius: 30px;
            margin-bottom: 15px;
            background: #fff;
            box-shadow: 20px 2px 20px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form .btn {
            font-size: 16px;
            font-weight: bold;
            background: #3598dc;
            border: none;
            outline: none !important;
        }
        .login-form .btn:hover, .login-form .btn:focus {
            background: #2389cd;
        }
        .login-form a {
            color: #fff;
            text-decoration: underline;
        }
        .login-form a:hover {
            text-decoration: none;
        }
        .login-form form a {
            color: #7a7a7a;
            text-decoration: none;
        }
        .login-form form a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="login-form">
    <form action="signUp" method="POST">
        <font color="#3598dc">
        <h2 class="text-center">Invalid Credentials!</h2>
            <h2 class="text-center">Please check password and confirmationPassword!</h2>
        </font>

    </form>

</div>
</body>
</html>