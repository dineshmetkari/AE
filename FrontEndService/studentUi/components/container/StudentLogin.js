import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {Link} from 'react-router';
import axios from 'axios';
import {browserHistory} from 'react-router';

export default class Login extends React.Component {
constructor(props){
  super(props);
  this.state={
  loggedin: false,
  data : '',
  name : '',
  }
 }

 handleLogin(){
   let a = this.refs.emailId.getValue();
   let b =this.refs.password.getValue();
   //console.log(a + " " + b);
   let ur='http://localhost:8083/students/studentlogin/'+a+'/'+b;
   axios.get(ur).then((response) => {
     this.setState({
       data  : response.data
     });

     this.setState({name : a});

     //console.log("name = " + this.state.name);

     let output= this.state.data;

     if(output == "Login Successfull"){
       loggedin : true;
       browserHistory.push({pathname : '/loginMain', state : this.state.name});
       return;
     }else{
       loggedin : false;
       browserHistory.push('/error');
       return;
     }
   });
 }

render() {
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
               type="password"
               onChange = {(event,newValue) => this.setState({password:newValue})}
               />
             <br/>
             <br/>
             <RaisedButton label="Login" primary={true} onClick={this.handleLogin.bind(this)} />
             <br/>

             <br/>

             <h4>Notyet Registered, Register <Link to="/register">Here</Link></h4>
            </div>
    );
  }
}
const style = {
  margin: 8,
 marginLeft: 600
};
