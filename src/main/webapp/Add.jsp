<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Questions</title>
    <style type="text/css">
      .form-style-1 {
        margin: 10px auto;
        max-width: 400px;
        padding: 20px 12px 10px 20px;
        font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
      }
      .form-style-1 li {
        padding: 0;
        display: block;
        list-style: none;
        margin: 10px 0 0 0;
      }
      .form-style-1 label {
        margin: 0 0 3px 0;
        padding: 0px;
        display: block;
        font-weight: bold;
      }
      .form-style-1 input[type="text"],
      textarea,
      select {
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        border: 1px solid #bebebe;
        padding: 7px;
        margin: 0px;
        -webkit-transition: all 0.3s ease-in-out;
        -moz-transition: all 0.3s ease-in-out;
        -ms-transition: all 0.3s ease-in-out;
        -o-transition: all 0.3s ease-in-out;
        outline: none;
      }
      .form-style-1 input[type="text"]:focus,
      .form-style-1 textarea:focus,
      .form-style-1 select:focus {
        -moz-box-shadow: 0 0 8px #88d5e9;
        -webkit-box-shadow: 0 0 8px #88d5e9;
        box-shadow: 0 0 8px #88d5e9;
        border: 1px solid #88d5e9;
      }
      .form-style-1 .field-divided {
        width: 49%;
      }

      .form-style-1 .field-long {
        width: 100%;
      }
      .form-style-1 .field-select {
        width: 100%;
      }
      .form-style-1 .field-textarea {
        height: 100px;
      }
      .form-style-1 input[type="submit"],
      .form-style-1 input[type="button"] {
        background: #4b99ad;
        padding: 8px 15px 8px 15px;
        border: none;
        color: #fff;
      }
      .form-style-1 input[type="submit"]:hover,
      .form-style-1 input[type="button"]:hover {
        background: #4691a4;
        box-shadow: none;
        -moz-box-shadow: none;
        -webkit-box-shadow: none;
      }
      .form-style-1 .required {
        color: red;
      }
    </style>
  </head>
  <body>
  
  <input type = "hidden" id="status" value="<%= request.getAttribute("status") %>">
    <form method="post" action="Add" >
      <ul class="form-style-1">
      <li>
          <label>Company Name <span class="required">*</span></label>
          <input type="text" name="Name" id="field1" placeholder="Enter Your Name" required />
        </li>
        <li>
          <label>Your Question <span class="required">*</span></label>
          <textarea
            name="Question"
            id="field5"
            class="field-long field-textarea"
          ></textarea>
        </li>
        <li>
          <input type="submit" value="Add" />
        </li>
      </ul>
    </form>
    
    <!-- JS -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">

<script type="text/javascript">
var status = document.getElementById("status").value;
if(status=="success")
	{
	swal("Congrats","Account Created Successfully","success");
	}
if(status=="invalidQuestions")
{
swal("Sorry","Please Write Your Question","error");
}
if(status=="invalidname")
{
swal("Sorry","Please Write Your Name","error");
}
</script>
    
  </body>
</html>
