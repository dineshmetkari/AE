import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {Link} from 'react-router';
import axios from 'axios';
import {browserHistory} from 'react-router';
const style = {
  display:'flex',
  flexDirection:'column',
  alignItems:'center'
};
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
   let ur='http://172.23.239.157:8089/admin/adminlogin/'+a+'/'+b;
   axios.get(ur).then((response) => {
     this.setState({
       data  : response.data,
     });

     this.setState({name : a});

     let output= this.state.data;

     if(output == "Login Successfull"){
       loggedin : true;

       browserHistory.push({pathname : '/adminMain', state : this.state.name});
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
               style={{marginBottom:'20px'}}
               />

             <RaisedButton label="Login" primary={true} onClick={this.handleLogin.bind(this)} />

            </div>
    );
  }
}
