import React ,{ Component } from 'react';
import FlatButton from 'material-ui/FlatButton';
const style = {
  margin: 12,
  float: 'right',
  backgroundColor: 'blue',
  color: 'white'
};

export default class NavButton extends Component{

render(){
  return(
  <div>
    <FlatButton label="logout" secondary={true} style={style} hoverColor={"orange"} href="http:3001/logout"  />
    <FlatButton label="Dashboard" secondary={true} style={style} hoverColor={"orange"} href="http:3001/dahsboard" />
  </div>
);}

}
