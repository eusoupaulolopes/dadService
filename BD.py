
# coding: utf-8

# In[1]:


import pandas as pd
import unicodedata
import datetime


# In[2]:


url_componentes = 'componentes-graduacao.csv'
url_unidades = 'http://dados.ufrn.br/dataset/da6451a5-1a59-4630-bdc2-97f6be4a59c2/resource/3f2e4e32-ef1a-4396-8037-cbc22a89d97f/download/unidades.csv'
url_docentes = 'http://dados.ufrn.br/dataset/8bf1a468-48ff-4f4d-95ee-b17b7a3a5592/resource/ff0a457e-76fa-4aca-ad99-48aebd7db070/download/docentes.csv'
url_avaliacoes = 'http://dados.ufrn.br/dataset/d5723d75-7e6e-4264-82aa-b96909b69f63/resource/7accd1d2-2793-460e-b98d-87a0679b9155/download/avaliacaodocencia.csv'


# In[3]:


df_unidades = pd.read_csv(url_unidades, ';')
df_unidades = df_unidades[['id_unidade_responsavel', 'unidade_responsavel']]
df_unidades.rename(columns={'id_unidade_responsavel': 'id_unidade', 'unidade_responsavel': 'lotacao'}, inplace=True)
df_unidades = df_unidades[(df_unidades['lotacao'] == 'DEPARTAMENTO DE INFORMATICA E MATEMATICA APLICADA') | (df_unidades['lotacao'] == 'INSTITUTO METROPOLE DIGITAL')].drop_duplicates()
df_unidades.reset_index(inplace=True, drop=True)
df_unidades.head()


# In[4]:


df_docentes = pd.read_csv(url_docentes, ';')
df_docentes.columns


# In[5]:


df_docentes = df_docentes[df_docentes['categoria'] == 'PROFESSOR DO MAGISTERIO SUPERIOR']


# In[6]:


df_docentes = df_docentes[['id_servidor', 'nome', 'formacao', 'id_unidade_lotacao', 'admissao']]
df_docentes.rename(columns={'id_servidor': 'id_docente', 'admissao': 'data_admissao', 'id_unidade_lotacao': 'id_unidade'}, inplace=True)
df_docentes = df_docentes.merge(df_unidades[['id_unidade']], on='id_unidade', how='inner')
df_docentes['data_admissao'] = [ data.date() for data in pd.to_datetime(df_docentes['data_admissao'])]
df_docentes['id_unidade'].value_counts()


# In[7]:


df_componentes = pd.read_csv(url_componentes, sep=',')
df_componentes = df_componentes[(df_componentes['unidade_responsavel'] == 'DEPARTAMENTO DE INFORMÁTICA E MATEMÁTICA APLICADA') | (df_componentes['unidade_responsavel'] == 'INSTITUTO METROPOLE DIGITAL')]
df_componentes.head()


# In[8]:


unidades_normalizadas = [unicodedata.normalize('NFKD', unicode(nome, "utf-8")).encode('ASCII', 'ignore') for nome in df_componentes['unidade_responsavel']]
series = pd.Series(data=unidades_normalizadas, index=df_componentes.index)
df_componentes['unidade_responsavel'] = series


# In[9]:


df_componentes = df_componentes.merge(df_unidades[['id_unidade', 'lotacao']], left_on='unidade_responsavel', right_on='lotacao', how='inner')


# In[10]:


df_componente_curricular = df_componentes[['id_componente', 'codigo']]
df_componente_curricular.head()


# In[11]:


df_componente_lotacao = df_componentes[['codigo', 'id_unidade', 'nome']]
df_componente_lotacao.head()


# In[12]:


turma_periodo = ['http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/5e77d066-d506-45eb-a21e-76aa79616fef/download/turmas-2017.1',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/01fe7343-fdf0-4a67-b915-2386b7c2fecb/download/turmas-2017.2',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/322d9977-ba15-47f1-8216-75a1ca78e197/download/turmas-2016.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/5e8e3228-7f22-40a2-9efd-561c44844567/download/turmas-2016.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/4d5aee5a-00b0-4ed6-a4be-59fa77a56797/download/turmas-2015.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/7c59621c-4a8b-49d4-b319-83cfea9bdf28/download/turmas-2015.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/e6e4144f-4042-4fdc-84e0-76e9ec27ae7c/download/turmas-2014.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/2c69547b-920f-4ec2-92c0-3fbc19512165/download/turmas-2014.2.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/0d96d930-8058-4def-9044-c3ae04c1f40c/download/turmas-2013.1.csv',
'http://dados.ufrn.br/dataset/1938623d-fb07-41a4-a55a-1691f7c3b8b5/resource/e7e3cf12-a29b-491a-a895-021a43819197/download/turmas-2013.2.csv',]


# In[13]:


frames = [pd.read_csv(periodo, sep=';', usecols=['id_turma', 'ano', 'nivel_ensino', 'periodo', 'id_componente_curricular']) for periodo in turma_periodo]


# In[14]:


df_turmas = pd.concat(frames)


# In[15]:


df_turmas.rename(columns={'nivel_ensino': 'nivel', 'id_componente_curricular': 'id_componente'}, inplace=True)
df_turmas = df_turmas[df_turmas['nivel'] == 'GRADUAÇÃO']
df_turmas['periodo'] = df_turmas['periodo'].astype(int) 
df_turmas['id_componente'] = df_turmas['id_componente'].astype(int)
df_turmas = df_turmas.merge(df_componente_curricular[['id_componente']], on='id_componente', how='inner')
df_turmas.head()


# In[16]:


df_avaliacoes = pd.read_csv(url_avaliacoes, sep=';', usecols=['id_docente', 'id_turma', 'qtd_discentes', 'postura_profissional_media', 'postura_profissional_DP', 'atuacao_profissional_media', 'atuacao_profissional_DP'])
df_avaliacoes = df_avaliacoes.merge(df_turmas[['id_turma']].astype(int), on='id_turma', how='inner')
df_avaliacoes = df_avaliacoes.merge(df_docentes[['id_docente']].astype(int), on='id_docente', how='inner')
df_avaliacoes.rename(columns={'postura_profissional_media': 'postura_profissional', 'atuacao_profissional_media': 'atuacao_profissional'}, inplace=True)
df_avaliacoes.head()


# In[17]:


matriculas = [
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/55dfe713-ff7c-4fa8-8d1d-d4294a025bff/download/matricula-componente-20172',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/79071c21-e32c-438f-b930-d1b6ccc02ec2/download/matricula-componente-20171',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/f6179838-b619-4d7d-af9c-18c438b80dd4/download/matriculas-de-2016.2.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/4778d3ce-8898-46a8-a623-ee6a480a2980/download/matriculas-de-2016.1.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/baa6c8b4-2072-417f-b238-c028ccc8c14b/download/matriculas-de-2015.2.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/9e7ba1c2-f92d-4b9c-9e91-3b026ecdf913/download/matriculas-de-2015.1.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/e974792c-b557-470c-bf3d-ede7d5b5e6a6/download/matricula-componente-20142.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/7081446d-39f9-4374-ad0b-86ecab97e569/download/matricula-componente-20141.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/517ed5f6-f8a2-40fd-826b-6ed3388f6e88/download/matricula-componente-20132.csv',
    'http://dados.ufrn.br/dataset/c8650d55-3c5a-4787-a126-d28a4ef902a6/resource/674de4cc-1fc0-4314-9f04-a38f0e1f9225/download/matricula-componente-20131.csv',
]


# In[18]:


dfs_matricula = [pd.read_csv(matricula, sep=';') for matricula in matriculas]


# In[19]:


df_matriculas = pd.concat(dfs_matricula)


# In[20]:


df_matriculas = df_matriculas[~df_matriculas['descricao'].isin(['CUMPRIU', 'INDEFERIDO', 'EXCLUIDA'])]


# In[21]:


df_matriculas.replace('APROVADO POR NOTA', 'APROVADO', inplace=True)
df_matriculas.replace(['REPROVADO POR NOTA', 'REPROVADO POR FALTAS', 'REPROVADO POR MÉDIA E POR FALTAS', 'REPROVADO POR NOTA E FALTA', 'DESISTENCIA', 'TRANCADO', 'CANCELADO'], 'REPROVADO', inplace=True)


# In[22]:


df_matriculas = df_matriculas.merge(df_turmas[['id_turma']].astype(int), on='id_turma', how='inner')
df_matriculas.head()


# In[23]:


df_aprovados = df_matriculas[df_matriculas['descricao'] == 'APROVADO']
df_media_aprovados = df_aprovados.groupby('id_turma')['media_final'].mean().to_frame('media_aprovados').reset_index(level=0)

df_media_aprovados.head()


# In[24]:


#media_turma = df_matriculas.groupby('id_turma')['media_final'].mean().to_frame('média').reset_index(level=0)
#media_turma.head()


# In[25]:


df_porcentagem = df_matriculas.groupby('id_turma')['descricao'].value_counts(normalize=True).to_frame('aprovados')


# In[26]:


df_porcentagem = df_porcentagem.reset_index(level=[0, 1])
df_porcentagem = df_porcentagem[~df_porcentagem['descricao'].isin(['REPROVADO'])]
df_porcentagem = df_porcentagem[['id_turma', 'aprovados']]
df_porcentagem.head()


# In[27]:


df_avaliacoes = df_avaliacoes.merge(df_media_aprovados, on='id_turma', how='left')
df_avaliacoes = df_avaliacoes.merge(df_porcentagem, on='id_turma', how='left')
df_avaliacoes.head()


# In[28]:


df_avaliacoes.to_csv('avaliacao.csv', sep=';', index=False)
df_componente_lotacao.to_csv('componente_lotacao.csv', sep=';', index=False)
df_componente_curricular.to_csv('componente_curricular.csv', sep=';', index=False)
df_docentes.to_csv('docente.csv', sep=';', index=False)
df_unidades.to_csv('unidade.csv', sep=';', index=False)
df_turmas.to_csv('turma.csv', sep=';', index=False)

