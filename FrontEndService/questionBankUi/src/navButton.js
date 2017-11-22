import React ,{ Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
const style = {
  margin: 12,
  float: 'right'
};

export default class NavButton extends Component{

render(){
  return(
  <div>
    <FlatButton label="logout" secondary={true} style={style} href="http:3001/logout" />
    <FlatButton label="Dashboard" primary={true} style={style} href="http:3001/dahsboard" />
  </div>
);}

}
