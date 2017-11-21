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

  }
 }

render() {
    return (

          <div style={style}>
          <h3><Link to="/adminlogin">Admin Login</Link></h3>
          <br/>
          <h3><Link to="/studentLogin">Student Login</Link></h3>
            </div>
    );
  }
}
const style = {
  margin: 8,
 marginLeft: 600
};
