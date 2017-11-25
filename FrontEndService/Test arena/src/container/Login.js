import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import {Link} from 'react-router';
export default class Login extends React.Component {
constructor(props){
  super(props);
  this.state={
  username:'',
  password:''
  }
 }
render() {
    return (

          <div style={style}>
           <TextField
             hintText="Enter your Username"
             floatingLabelText="Username"
             onChange = {(event,newValue) => this.setState({username:newValue})}
             />
           <br/>
             <TextField
               type="password"
               hintText="Enter your Password"
               floatingLabelText="Password"
               onChange = {(event,newValue) => this.setState({password:newValue})}
               />
             <br/>
             <br/>
             <RaisedButton label="Login" primary={true} containerElement={<Link to="/loginMain"/>}/>
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
