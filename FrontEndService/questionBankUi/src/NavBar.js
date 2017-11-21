import React, { Component } from 'react';
import logo from './logo1.png';

class AppBar extends Component{
    render(){
        return(
        <div style={{height:'55px', width:'100vw',backgroundColor:'#00bcd4 '}}>
        <img src={logo} alt="logo" style={{height:'50px',width:'250px', marginTop:'2px'}}  />
        </div>
    );
    }

}

export default AppBar;
