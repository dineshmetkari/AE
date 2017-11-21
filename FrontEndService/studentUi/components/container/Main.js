import React from 'react';
import ReactDom from 'react-dom';
//import AdminLogin from './AdminLogin';
//import Register from './Register';
//import StudentHome from './StudentHome';
import HomePage from './HomePage';


export default class Main extends React.Component{
	constructor(props){
		super(props);


	}
	render(){
		return(
			<div>
			<HomePage />

			</div>

			);
	}
	search(jdata){
		console.log("Fetch_json")
		this.setState({resdata:jdata});
	}
}
