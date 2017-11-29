
import logo from './logo1.png';
import React, { Component } from 'react';

 export default class Appbar2 extends Component{
	render(){

		return(
		<div style={{height:'70px', width:'100vw',backgroundColor:'#00bcd4'}}>
		<img src={logo} alt="logo" style={{height:'50px',width:'250px',padding:'0px', marginTop:'1vh', border:'0px'}}  />
    <div style={{display:'flex', flexDirection:'row',justifyContent:'flex-end'}}>

    </div>

		</div>
	);
	}

}
