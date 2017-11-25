import logo from './logo1.png';
import React, { Component } from 'react';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import IconButton from 'material-ui/IconButton';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import NavButton from './navButton'
const style = {
  menu:{
    float: 'right'
  },
  bar:{
    height : '55px',
    width : '100%',
    backgroundColose: '#00bcd4 '
  }
}
class AppBar extends Component{
    render(){
        return(
        <div style={style.bar}>
        <NavButton/>
        <img src={logo} alt="logo" style={{height:'51px',width:'250px', marginTop:'2px'}}  />

        </div>
    );
    }

}

export default AppBar;
