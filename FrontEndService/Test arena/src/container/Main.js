import React from 'react';
//import AdminLogin from './AdminLogin';
//import Register from './Register';
//import StudentHome from './StudentHome';
import HomePage from './HomePage';


export default class Main extends React.Component{

	render(){
		return(
			<div>
			<HomePage />
			</div>

			);
	}
	search(jdata){
		this.setState({resdata:jdata});
	}
}
