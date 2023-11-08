import React from 'react';
import {View, Text, TextInput, StyleSheet, FlatList, SafeAreaView } from 'react-native';
import { List, Divider } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';

const DATA = [
    {
        ID: 1,
        produto: 'Mouse Wireless',
        quantidade: <TextInput>
        lable = 'quantidade'
        </TextInput>
    },
    {
        ID: 2,
        produto: 'Teclado Wireless',
    },
    {
        ID:3,
        produto: 'Suporte para Notebook'
    }
];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function History() {


    const renderItem = ({item}) => (<><List.Item title={item.produto} 
    right={() => <List.Item title ={item.loja}/> }
     />
     <Divider theme={{ colors: { primary: 'green' } }} /></>)
  return (

    <View style={styles.container}>
        <Header title = "Loja Lótus"/>
        <List.Item 
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Produto' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Quantidade'
            />
          }
        />
        <Divider style={{height: 3, backgroundColor: "#0025bf"}} />
        <View style={{color: '#000', height:10 }}/>
        <FlatList
        data={DATA}
        renderItem={renderItem}
        keyExtractor={item => item.ID}
      />
        <StatusBar style="auto" />
    </View>
    
    );
}


const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff'
    },
    listFirst: {
        fontWeight: 'bold' ,
        marginTop: 50 }
  });



  