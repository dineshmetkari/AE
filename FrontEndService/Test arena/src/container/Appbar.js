import React, { Component } from 'react';
import logo from './logo1.png';

class Appbar extends Component{
	render(){
		return(
		<div style={{height:'70px', width:'100vw',backgroundColor:'#00bcd4'}}>
		<img src={logo} alt="logo" style={{height:'50px',width:'250px',padding:'0px', marginTop:'1vh', border:'0px'}}  />
		</div>
	);
	}

}

export default Appbar;
