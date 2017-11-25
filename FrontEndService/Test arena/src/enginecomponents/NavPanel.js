import React, { Component } from 'react';
import RaisedButton from 'material-ui/RaisedButton';
import Pagination from './Pagination';

class NavPanel extends Component{
	constructor(props){
		super(props);
		this.state={
			QNumber:1,
		};
		// this.onNavClick=this.onNavClick.bind(this);
		this.onNavPrevClick=this.onNavPrevClick.bind(this);
		this.onNavNextClick=this.onNavNextClick.bind(this);
		this.submit=this.submit.bind(this);
	}

	onNavPrevClick(){
		if(this.state.QNumber>1){
			this.setState({
				QNumber: this.state.QNumber-1
			});

		}
		this.props.questionClick(this.state.QNumber);
	}
	onNavNextClick(){
		if(this.state.QNumber< this.props.questionCount){
			this.setState({
				QNumber: this.state.QNumber+1
			});
		}
		this.props.questionClick(this.state.QNumber);
	}

	getQfromPager(NewQ){
		this.setState({
			QNumber: NewQ
		});
	
		this.props.questionClick(this.state.QNumber);
	}
	submit(){
		this.props.closeConnection();
	}
	render(){
		return(
		<div style={{display:'flex', flexDirection:'row', justifyContent:'space-between', marginRight:'', marginLeft:'', alignItems:'center'}}>

		<div style={{width:'40vw'}}>
		<Pagination
			qCount={this.props.questionCount}
			onPagerClick={this.getQfromPager.bind(this)}
			style={{display:'flex', marginTop:''}} />
		</div>

		<div style={{display:'flex', flexDirection:'column', alignItems:'flex-end'}}>
				<div style={{flexDirection:'row', marginTop:'10px', alignItems:'flex-end', flexWrap:'wrap'}}>
				<RaisedButton onClick={this.onNavPrevClick}
				label="< Back" primary={true} style={{marginRight:'5px'}}/>

				<RaisedButton onClick={this.onNavNextClick}
				label="Next >" primary={true} style={{marginRight:''}} />
				</div>

				<RaisedButton label="Submit" onClick={this.submit} fullWidth={true} style={{marginTop:'5px'}} backgroundColor="#46d246" labelColor="white" />

		</div>


		</div>
		);
	}
}

export default NavPanel;

/*style={{display:'flex', flexDirection:'row', justifyContent:'space-between'}}*/
