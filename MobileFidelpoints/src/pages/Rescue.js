import React from 'react';
import {View, Text, StyleSheet, FlatList } from 'react-native';
import { List, Divider } from 'react-native-paper';
import { StatusBar } from 'expo-status-bar';
import Header from '../components/Header';

const DATA = [
    {
        ID: 1,
        loja: 'Edglei',
        point: 200,
    },
    {
        ID: 2,
        loja: 'Ana',
        point: 400,
    },
    {
        ID:3,
        loja: 'Edivania',
        point: 600
    }
];

const Item = ({title}) => (
    <View style={styles.item}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );

export default function Rescue() {


    const renderItem = ({item}) => (<><List.Item title={item.loja} 
    right={() => <List.Item title ={item.point}/> }
     />
     <Divider theme={{ colors: { primary: 'green' } }} /></>)
  return (

    <View style={styles.container}>
        <Header title = "Resgate de pontos"/>
        <List.Item 
          titleStyle={{fontWeight: 'bold'}} 
          style={styles.listFirst} 
          title='Loja' 
          right={() => 
            <List.Item 
              titleStyle={{fontWeight: 'bold'}} 
              title= 'Produto'
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
