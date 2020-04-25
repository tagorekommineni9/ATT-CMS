var express = require('express');
var mysql = require('mysql');
var bodyparser = require('body-parser');

var con = mysql.createConnection({
  host: '127.0.0.1',
  user: 'root',
  password: 'admin',
  database: 'userdata'
});

// initialise express
var app = express();
app.use(bodyparser.json());
app.use(bodyparser.urlencoded({extended:true}));



app.post('/register/',(req,res,next)=>{
    console.log("req.body", req.body)
    var data = req.body;
    var fullname = data.fullname;
    var email = data.email;
    var phone = data.phone;
    var pass = data.pass;
   


    con.query( 'SELECT * FROM userdt where email=?' ,[email], function(err,result,fields){
            con.on('error',(err)=>{
                console.log('[MySQL ERROR]',err);
            });

            if(result && result.length){
                res.json('user exists !!!');
            }else{
                //con.query("INSERT INTO 'userdata'('id', 'email', 'password') VALUES (?,?,?)", [uid,email,password],function(err,result,fields){
                var sql = "INSERT INTO userdt (fullname,email,phone,pass) VALUES (?,?,?,?)";
                var values = [fullname,email,phone,pass];

                console.log(sql,values);

                con.query(sql, values ,function(err,result,fields){
                    con.on('error',(err)=>{
                        console.log('[MySQL ERROR]',err);
                    });
                    res.json('Register Successful');
                    console.log('registered');
                });
            }
        });
});


app.post('/registercomplaint/',(req,res,next)=>{
    console.log("req.body", req.body)
    var data = req.body;
    var location = data.location;
    var category = data.category;
    var comptype = data.comptype;
    var compdetails = data.compdetails;
   

con.query( 'INSERT INTO complaintdt (location,category,comptype,compdetails) VALUES (?,?,?,?);' ,[location,category,comptype,compdetails], function(err,result,fields){
		res.json('Complaint filed successfully');
			con.on('error',(err)=>{
				console.log('[MySQL ERROR]',err);
				res.json('Cannot regsiter complaint ');
			});
		});
});


app.post('/login/',(req,res,next)=>{
    var data = req.body;
    var pass = data.pass;
    var email = data.email;

    con.query('SELECT * FROM userdt where email = ?',[email],function(err,result,fields){
        con.on('error',(err)=>{
            console.log('[MySQL ERROR]',err);
        });
        
        if(result && result.length){


            // result = [ RowDataPacket { id: 1, email: 'abc@abc.com', password: 'pass' } ]

            if(pass == result[0].pass){
                res.json('Valid user'); 
            }else{
                res.json('Invalid user');
            }
        }
    });


});


//Change Email
app.post('/updateprofile/',(req,res,next)=>{
	var data = req.body;
	var pass = data.pass;
	var email = data.email;


	con.query( 'UPDATE userdata.userdt SET email = ? WHERE (pass = ?);' ,[email,pass], function(err,result,fields){
		res.json('Sucess ');
		console.log(email,pass);
			con.on('error',(err)=>{
				console.log('[MySQL ERROR]',err);
				res.json('Could not update profile');
			});
		});
});




// start node server
app.listen(3033,() => {
    console.log('server running on : http://localhost:%s',3033);
});
