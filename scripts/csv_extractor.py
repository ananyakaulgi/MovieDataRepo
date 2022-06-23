'''Python is one of the ways to be able to Extract CSV Dataset and convert it into the Structural form we need it to be in.'''

import pandas as pd
import mysql.connector
from mysql.connector import Error

#Opens MySql Connection, Executes a INSERT statement, sent in the query parameter
def mysql_execute(query, database):
    try:
        connection = mysql.connector.connect(host='localhost',
                                             database=database,
                                             user='root',
                                             password='Ananya30')

        cursor_conn = connection.cursor()
        print(query)
        result = cursor_conn.execute(query)
        print("Insert query executed successfully ")

    except mysql.connector.Error as error:
        print("Failed to execute query in MySQL: ".format(error))
    finally:
        if connection.is_connected():
            cursor_conn.close()
            connection.close()
            print("Connection to MySql is Closed.")


#Reads the Movies_metadata CSV and pulls the data from it. The data has many facets and I have assumed some simple situations.
def movies_read_file(filepath):
    title, budget, revenue, production_company, country, releaseDate, runTime, genres = "", 0, 0, "", "", "",0, []


    file = pd.read_csv(filepath, encoding="utf8")
    for line in file.iterrows():
        #print(len(eval(line[1]['genres'])))
        budget = line[1]['budget']
        revenue = line[1]['revenue']
        title = line[1]['original_title']
        movie_id = line[1]['id']

        #Production Companies is a LIST. This needs to be converted to a List object and parsed to create multiple Queries, if 1 movie has multiple Production companies
        #Currently, assumption is to Pull only the first one in the list
        production_company = eval(line[1]['production_companies'])[0]['name'] if len(eval(line[1]['production_companies']))>0 else ''
        prod_company_id = eval(line[1]['production_companies'])[0]['id'] if len(eval(line[1]['production_companies']))>0 else -1

        #This should be a list as well.
        # Currently, assumption is to Pull only the first one in the list
        country = eval(line[1]['production_countries'])[0]['name'] if len(eval(line[1]['production_countries']))>0 else ''

        releaseDate = line[1]['release_date']
        runTime = line[1]['runtime']

        #This should be a List as well.
        # Currently, assumption is to Pull only the first one in the list
        language = eval(line[1]['spoken_languages'])[0]['name'] if len(eval(line[1]['spoken_languages']))>0 else ''
        lang_id =  eval(line[1]['spoken_languages'])[0]['iso_639_1'] if len(eval(line[1]['spoken_languages'])) >0 else -1
        for value in range(len(eval(line[1]['genres']))):
            genres.append(eval(line[1]['genres'])[value]['name'])


        #Queries Formed based on the singular value of Production
        queryProduction = "insert IGNORE into production_company (id,name) values({},'{}')".format(prod_company_id,production_company)

        #Query formed based on the multiset of values from above
        queryMovies = "insert IGNORE into movies (id, movie_name, budget, revenue, release_date, run_time_duration, production_company_id,original_language_id)values({},'{}',{},{},{},{},{},'{}')".format(movie_id,title,budget,revenue,releaseDate,runTime,prod_company_id,lang_id)

        #Execute the MYSQL Statements
        mysql_execute(queryProduction, "movies")
        mysql_execute(queryMovies,"movies")

movies_read_file("../movies-dataset/movies_metadata.csv")



'''I have not been able to successfully test this particular execution to add data to the mysql database. However, this is the path I would be taking to complete this execution. It is not limited to the above execution alone. It could add better libraries or further steps to be precise.'''

