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
			this.state.QNumber= this.state.QNumber-1;
		}
		this.props.questionClick(this.state.QNumber);	
	}
	onNavNextClick(){
		if(this.state.QNumber< this.props.questionCount){
			this.state.QNumber= this.state.QNumber+1;
		}
		this.props.questionClick(this.state.QNumber);	
	}

	getQfromPager(NewQ){
		this.state.QNumber = NewQ;
		this.props.questionClick(this.state.QNumber);
	}
	submit(){
		this.props.closeConnection();
	}
	render(){
		return(
		<div style={{display:'flex', flexDirection:'row', justifyContent:'space-between', marginRight:'50px', marginLeft:'50px', alignItems:'center'}}>

		<Pagination 
			qCount={this.props.questionCount}
			onPagerClick={this.getQfromPager.bind(this)}  
			style={{display:'flex', marginTop:''}} />
		

		<div style={{display:'flex', flexDirection:'column', alignItems:'flex-end'}}>
			<div style={{marginTop:'10px'}}>
				<RaisedButton onClick={this.onNavPrevClick}
				label="< Previous" primary={true} style={{marginRight:'5px'}}/>
				
				<RaisedButton onClick={this.onNavNextClick}
				label="Next >" primary={true} style={{marginRight:'15px'}} />
				
				<RaisedButton 
				label="Mark" primary={true} />
			</div>
			<div style={{marginTop:'5px'}}>
				<RaisedButton label="Submit" onClick={this.submit} fullWidth={true} backgroundColor="#4d9900" labelColor="white" />
			</div>
		</div>


		</div>
		);
	}
}

export default NavPanel;

/*style={{display:'flex', flexDirection:'row', justifyContent:'space-between'}}*/
