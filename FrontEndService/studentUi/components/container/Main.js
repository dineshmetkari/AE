import React from 'react';
import ReactDom from 'react-dom';
import Login from './Login';
import Register from './Register';
import StudentHome from './StudentHome';


export default class Main extends React.Component{
	constructor(props){
		super(props);
	

	}
	render(){
		return(
			<div>
			<Login />

			</div>

			);
	}
	search(jdata){
		console.log("Fetch_json")
		this.setState({resdata:jdata});
	}
}
