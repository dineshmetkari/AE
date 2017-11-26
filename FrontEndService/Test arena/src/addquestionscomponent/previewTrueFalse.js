import React from 'react';
import AppBar from 'material-ui/AppBar';
import Drawer from 'material-ui/Drawer';
import RaisedButton from 'material-ui/RaisedButton';
import {RadioButton, RadioButtonGroup} from 'material-ui/RadioButton';
import {Card, CardText} from 'material-ui/Card';
  const styles = {
    block: {
      maxWidth: 250,
    },
    radioButton: {
      marginBottom: 16,
      marginLeft: 20
    },
    questionStyle:{
      marginLeft: 20
    },
    drawer:{
      width: '53%'
    }
  };
class PreviewTrueFalse extends React.Component{
  constructor(props){
    super(props)
    this.state={
                open: false,
                question:'',
                checkedTrue: false,
                checkedFalse: false
                }
    this.toggleDrawer = this.toggleDrawer.bind(this);
  }
  render(){
    return(
      <div>
        <Drawer width={styles.drawer.width} docked={false} openSecondary={true} open={this.state.open} onRequestChange={this.toggleDrawer}>
          <AppBar
            title="True/False Preview"
            iconClassNameRight="muidocs-icon-navigation-expand-more"
          />
    <Card initiallyExpanded="true">
        <h3 style = {styles.questionStyle}> {this.state.question} </h3>
        <RadioButtonGroup name="Choices" defaultSelected="yes">
        <RadioButton
          value="light"
          label="True"
          style={styles.radioButton}
        />
        <RadioButton
          value="not_light"
          label="False"
          style={styles.radioButton}
        />
        </RadioButtonGroup>
      <CardText expandable = {false}>
      </CardText>
      <RaisedButton
        label="close"
        onClick={this.props.setDefault}
      />
    </Card>
    </Drawer>
    </div>
);}
componentWillReceiveProps(newProps){
  this.setState({open : newProps.open});
  this.setState({question: newProps.question});
}
toggleDrawer(){
  console.log("called request change");
  this.setState({open: false});
}

updateCheckTrue() {
    this.setState((oldState) => {
      return {
        checkedTrue: !oldState.checkedTrue,
      };
    });
  }
  updateCheckFalse() {
      this.setState((oldState) => {
        return {
          checkedFalse: !oldState.checkedFalse,
        };
      });
    }

}

export default PreviewTrueFalse;
