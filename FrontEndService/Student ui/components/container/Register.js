import React from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
export default class  Register extends React.Component {
  constructor(props) {
    super(props);
    this.state={
      first_name:'',
      last_name:'',
      email:'',
      password:''
  }
}
render() {
    return (
          <div style={style}>
           <TextField
             hintText="Enter your First Name"
             floatingLabelText="First Name"
             onChange = {(event,newValue) => this.setState({first_name:newValue})}
             />
           <br/>
           <TextField
             hintText="Enter your Last Name"
             floatingLabelText="Last Name"
             onChange = {(event,newValue) => this.setState({last_name:newValue})}
             />
           <br/>
           <TextField
             hintText="Enter your Email"
             type="email"
             floatingLabelText="Email"
             onChange = {(event,newValue) => this.setState({email:newValue})}
             />
           <br/>
           <TextField
             type = "password"
             hintText="Enter your Password"
             floatingLabelText="Password"
             onChange = {(event,newValue) => this.setState({password:newValue})}
             />
           <br/>
           <br/>
           <RaisedButton label="Submit" primary={true}  onClick={(event) => this.handleClick(event)}/>
          </div>
    );
  }
}
const style = {
  margin: 15,
marginLeft: 600
};