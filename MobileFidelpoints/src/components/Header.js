import React from "react";
import { StyleSheet } from "react-native";
import { Appbar } from "react-native-paper";
import { useNavigation } from '@react-navigation/core';


function Header(props){
    const navigation = useNavigation();

    return(

            <Appbar.Header >
                <Appbar.Content 
                    title={props.title} 
                    titleStyle={{color:"#fff"}} 
                    style={styles.bar} 
                />
                <Appbar.Action icon='logout' style={{backgroundColor:'#fff'}} onPress={() => navigation.navigate('Login')} />
            </Appbar.Header>
    )
}
export default Header;

const styles = StyleSheet.create({
    bar:{
        backgroundColor:"#0025bf", 
        textDecorationColor:"#fff",
        marginHorizontal: 0,
        borderRadius: 5,
        height: 30,
        alignItems:'center',
        justifyContent: 'center'

    } 
})