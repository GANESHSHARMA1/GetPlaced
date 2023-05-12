<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>GetPlaced-Services</title>
    <link rel="stylesheet" href="Style.css" />
  </head>
  <body>
    <header>
      <nav>
        <div class="Logoplace">
          <a href="index-new.jsp">
            <img
              class="Img"
              src="Img/logo.jpg"
              alt="Logo Image"
              width="60px"
              height="54px"
            />
            <h1 class="logoname">Get<br />Placed</h1>
          </a>
        </div>
        <ul>
          <li>
            <a href="index-new.jsp">Home</a>
          </li>
          <li>
            <a href="Services-new.jsp">Services</a>
          </li>
          <li>
            <a href="Placement-new.jsp">Placement</a>
          </li>
          <li>
            <a href="About-us-new.jsp">About Us</a>
          </li>
          <li>
            <a href="Contact-new.jsp">Contact Us</a>
          </li>
        </ul>
        <a class="login" href="Logout">Logout</a>
      </nav>
    </header>
    <main>
      <div class="main-contact">
        <div class="container">
          <div id="div11">
            <h1>Contact Form</h1>
            <p id="contact">Please fill all the text in the fields.</p>
          </div>
          <form method="post" action="Contact">
            <label for="name">Your Name:</label>
            <input
              type="text"
              name="Name"
              id="name"
              placeholder="Enter Your Name"
              required
            />
            <br />
            <label for="gmail">Your Email:</label>
            <input
              type="email"
              name="Gmail-id"
              id="gmail"
              placeholder="Enter your Mail id"
              required
            />
            <br />
            <label for="phone">Your Mobile Number:</label>
            <input
              type="number"
              name="Phone"
              id="phone"
              placeholder="Enter your Mobile Number"
              required
            />
            <br />
            <label for="designation">Your Designation:</label>
            <select name="Designation" id="designation" required>
              <option value="--Select--">--Select--</option>
              <option value="Teacher">Teacher</option>
              <option value="Student">Student</option>
              <option value="Other">Other</option></select
            ><br />
            <label for="message">Your Message to us:</label><br />
            <textarea
              name="Message"
              id="message"
              cols="30"
              rows="10"
              placeholder="Enter Your Message..."
            ></textarea
            ><br />
            <input type="submit" value="Submit" /><input
              type="reset"
              value="Reset"
            />
          </form>
        </div>
      </div>
    </main>
    <footer>
      <p class="footer">Copyright &copy;2022 GetPlaced, All Right Reserved.</p>
    </footer>
    
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
if(status=="invalidName")
{
swal("Sorry","Please Enter Name","error");
}
if(status=="invalidEmail")
{
swal("Sorry","Please Enter Email","error");
}
if(status=="invalidMobile")
{
swal("Sorry","Please Enter Contact Number","error");
}
if(status=="invalidMobileLength")
{
swal("Sorry","Please Enter Valid Contact Number","error");
}
if(status=="invalidmessage")
{
swal("Sorry","Please Enter Message","error");
}
</script>
  </body>
</html>
