import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {Link} from 'react-router';
import axios from 'axios';
// import FlatButton from 'material-ui/FlatButton';
import {browserHistory} from 'react-router';

export default class Login extends React.Component {
constructor(props){
  super(props);
  this.state={
  open: false,
  // loggedin: false,
  data : '',
  name : '',
  }
 }
 handleOpen = () => {
    this.setState({open: true});
  };

  handleClose = () => {
    this.setState({open: false});
  };

 // handleValidation(){
 //    let fields = this.state.fields;
 //     let errors = {};
 //     let formIsValid = true;
 //
 //     //Email Id
 //     if(!fields["emailId"]){
 //        formIsValid = false;
 //        errors["emailId"] = "Can not be empty";
 //     }
 //
 //     //Password
 //     if(!fields["password"]){
 //        formIsValid = false;
 //        errors["password"] = "Can not be empty";
 //     }
 //     this.setState({errors: errors});
 //     return formIsValid;
 // }

 handleLogin(){
   let a = this.refs.emailId.getValue();
   let b =this.refs.password.getValue();
   //console.log(a + " " + b);
   let ur='http://172.23.238.205:8083/students/studentlogin/'+a+'/'+b;
   axios.get(ur).then((response) => {
     this.setState({
       data  : response.data
     });

     this.setState({name : a});

     //console.log("name = " + this.state.name);

     let output= this.state.data;

     if(output === "Login Successfull"){
      //  loggedin : true;
       browserHistory.push({pathname : '/loginMain', state : this.state.name});

       return;
     }else{
      //  loggedin : false;

       alert("Username and Password must be enter, and valid Username and Password");
       return;
     }
   });
 }
 handleChange(field, e){
        let fields = this.state.fields;
        fields[field] = e.target.value;
        this.setState({fields});
    }

render() {
  // const actions = [
  //    <FlatButton
  //      label="Cancel"
  //      primary={true}
  //      onClick={this.handleClose}
  //    />,
  //    <FlatButton
  //      label="Submit"
  //      primary={true}
  //      keyboardFocused={true}
  //      onClick={this.handleClose}
  //    />,
  //  ];
    return (

          <div style={style}>
           <TextField ref='emailId'
             hintText="Enter your Username"
             floatingLabelText="Username"
             onChange = {(event,newValue) => this.setState({username:newValue})}
             />
           <br/>
             <TextField ref='password'
               type="password"
               hintText="Enter your Password"
               floatingLabelText="Password"
               onChange = {(event,newValue) => this.setState({password:newValue})}
               />
             <br/>
             <br/>
             <RaisedButton label="Login" primary={true} onClick={this.handleLogin.bind(this)} />
             <br/>

             <br/>

             <p>Not yet registered? Register <Link to="/register">here</Link></p>
            </div>
    );
  }
}
const style = {
  margin: 8,
 marginLeft: 600
};
