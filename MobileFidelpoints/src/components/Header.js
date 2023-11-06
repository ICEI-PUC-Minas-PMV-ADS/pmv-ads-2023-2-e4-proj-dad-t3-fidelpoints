import React from "react";
import { StyleSheet } from "react-native";
import { Appbar } from "react-native-paper";


function Header(props){

    return(

            <Appbar.Header>
                <Appbar.Content title={props.title} titleStyle={{color:"#fff"}} style={styles.bar} />
            </Appbar.Header>
    )
}
export default Header;

const styles = StyleSheet.create({
    bar:{
        backgroundColor:"#0025bf", 
        textDecorationColor:"#fff", 
        margin: 10, 
        borderRadius: 5,
        height: 40,
        alignItems:'center',
        justifyContent: 'center'

    } 
})