USE [master]
GO
/****** Object:  Database [FootlendarDB]    Script Date: 16.06.2019 15:44:15 ******/
CREATE DATABASE [FootlendarDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'FootlendarDB', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.WINCCPLUSMIG2014\MSSQL\DATA\FootlendarDB.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'FootlendarDB_log', FILENAME = N'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.WINCCPLUSMIG2014\MSSQL\DATA\FootlendarDB_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [FootlendarDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [FootlendarDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [FootlendarDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [FootlendarDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [FootlendarDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [FootlendarDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [FootlendarDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [FootlendarDB] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [FootlendarDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [FootlendarDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [FootlendarDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [FootlendarDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [FootlendarDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [FootlendarDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [FootlendarDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [FootlendarDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [FootlendarDB] SET  ENABLE_BROKER 
GO
ALTER DATABASE [FootlendarDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [FootlendarDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [FootlendarDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [FootlendarDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [FootlendarDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [FootlendarDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [FootlendarDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [FootlendarDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [FootlendarDB] SET  MULTI_USER 
GO
ALTER DATABASE [FootlendarDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [FootlendarDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [FootlendarDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [FootlendarDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [FootlendarDB] SET DELAYED_DURABILITY = DISABLED 
GO
USE [FootlendarDB]
GO
/****** Object:  Table [dbo].[Matches]    Script Date: 16.06.2019 15:44:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Matches](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[home] [varchar](20) NOT NULL,
	[away] [varchar](20) NOT NULL,
	[startTime] [datetime] NOT NULL,
	[descr] [varchar](100) NULL,
	[scoreHome] [int] NULL,
	[scoreAway] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teams]    Script Date: 16.06.2019 15:44:16 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teams](
	[name] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Matches] ON 

INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (8, N'Tahiti U20', N'Senegal U20', CAST(N'2019-05-23T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 1', 0, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (9, N'Meksyk U20', N'Włochy U20', CAST(N'2019-05-23T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 1', 1, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (10, N'Polska U20', N'Kolumbia U20', CAST(N'2019-05-23T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 1', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (11, N'Japonia U20', N'Ecuador U20', CAST(N'2019-05-23T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 1', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (12, N'Katar U20', N'Nigeria U20', CAST(N'2019-05-24T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 1', 0, 4)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (13, N'Honduras U20', N'Nowa Zelandia U20', CAST(N'2019-05-24T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 1', 0, 5)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (14, N'Urugwaj U20', N'Norwegia U20', CAST(N'2019-05-24T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 1', 3, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (15, N'Ukraina U20', N'USA U20', CAST(N'2019-05-24T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 1', 2, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (16, N'Portugalia U20', N'Korea Po?udniowa U20', CAST(N'2019-05-25T15:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 1', 1, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (17, N'Panama U20', N'Mali U20', CAST(N'2019-05-25T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 1', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (18, N'Francja U20', N'Arabia Saudyjska U20', CAST(N'2019-05-25T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 1', 2, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (19, N'Argentyna U20', N'RPA U20', CAST(N'2019-05-25T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 1', 5, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (20, N'Meksyk U20', N'Japonia U20', CAST(N'2019-05-26T15:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 2', 0, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (21, N'Senegal U20', N'Kolumbia U20', CAST(N'2019-05-26T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 2', 2, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (22, N'Ecuador U20', N'Włochy U20', CAST(N'2019-05-26T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 2', 0, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (23, N'Polska U20', N'Tahiti U20', CAST(N'2019-05-26T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 2', 5, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (24, N'Katar U20', N'Ukraina U20', CAST(N'2019-05-27T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 2', 0, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (25, N'Honduras U20', N'Urugwaj U20', CAST(N'2019-05-27T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 2', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (26, N'USA U20', N'Nigeria U20', CAST(N'2019-05-27T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 2', 2, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (27, N'Norwegia U20', N'Nowa Zelandia U20', CAST(N'2019-05-27T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 2', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (28, N'Portugalia U20', N'Argentyna U20', CAST(N'2019-05-28T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 2', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (29, N'Panama U20', N'Francja U20', CAST(N'2019-05-28T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 2', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (30, N'RPA U20', N'Korea Po?udniowa U20', CAST(N'2019-05-28T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 2', 0, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (31, N'Arabia Saudyjska U20', N'Mali U20', CAST(N'2019-05-28T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 2', 3, 4)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (32, N'Włochy U20', N'Japonia U20', CAST(N'2019-05-29T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 3', 0, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (33, N'Ecuador U20', N'Meksyk U20', CAST(N'2019-05-29T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa B Kolejka 3', 1, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (34, N'Senegal U20', N'Polska U20', CAST(N'2019-05-29T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 3', 0, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (35, N'Kolumbia U20', N'Tahiti U20', CAST(N'2019-05-29T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa A Kolejka 3', 6, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (36, N'Nowa Zelandia U20', N'Urugwaj U20', CAST(N'2019-05-30T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 3', 0, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (37, N'Norwegia U20', N'Honduras U20', CAST(N'2019-05-30T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa C Kolejka 3', 12, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (38, N'USA U20', N'Katar U20', CAST(N'2019-05-30T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 3', 1, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (39, N'Nigeria U20', N'Ukraina U20', CAST(N'2019-05-30T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa D Kolejka 3', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (41, N'Arabia Saudyjska U20', N'Panama U20', CAST(N'2019-05-31T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 3', 1, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (42, N'RPA U20', N'Portugalia U20', CAST(N'2019-05-31T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 3', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (43, N'Korea Po?udniowa U20', N'Argentyna U20', CAST(N'2019-05-31T20:30:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa F Kolejka 3', 2, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (44, N'Mali U20', N'Francja U20', CAST(N'2019-05-31T18:00:00.000' AS DateTime), N'Faza grupowa MŚ U 20 Grupa E Kolejka 3', 2, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (45, N'Włochy U20', N'Polska U20', CAST(N'2019-06-02T17:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 1, 0)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (46, N'Kolumbia U20', N'Nowa Zelandia U20', CAST(N'2019-06-02T20:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (47, N'Urugwaj U20', N'Ecuador U20', CAST(N'2019-06-03T17:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 1, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (48, N'Ukraina U20', N'Panama U20', CAST(N'2019-06-03T17:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 4, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (49, N'Senegal U20', N'Nigeria U20', CAST(N'2019-06-03T20:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 2, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (50, N'Japonia U20', N'Korea Po?udniowa U20', CAST(N'2019-06-03T17:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 0, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (51, N'Francja U20', N'USA U20', CAST(N'2019-06-04T17:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 2, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (52, N'Argentyna U20', N'Mali U20', CAST(N'2019-06-04T20:30:00.000' AS DateTime), N'Faza finałowa 1/8 MŚ U 20', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (53, N'Kolumbia U20', N'Ukraina U20', CAST(N'2019-06-07T15:30:00.000' AS DateTime), N'Faza finałowa 1/4 MŚ U 20', 1, 1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (54, N'Włochy U20', N'Mali U20', CAST(N'2019-06-07T18:30:00.000' AS DateTime), N'Faza finałowa 1/4 MŚ U 20', 4, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (55, N'USA U20', N'Ecuador U20', CAST(N'2019-06-08T17:30:00.000' AS DateTime), N'Faza finałowa 1/4 MŚ U 20', 1, 2)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (56, N'Korea Po?udniowa U20', N'Senegal U20', CAST(N'2019-06-08T20:30:00.000' AS DateTime), N'Faza finałowa 1/4 MŚ U 20', 3, 3)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (57, N'Ukraina U20', N'Włochy U20', CAST(N'2019-06-11T17:30:00.000' AS DateTime), N'Faza finałowa 1/2 MŚ U20', -1, -1)
INSERT [dbo].[Matches] ([id], [home], [away], [startTime], [descr], [scoreHome], [scoreAway]) VALUES (58, N'Ecuador U20', N'Korea Po?udniowa U20', CAST(N'2019-06-11T20:30:00.000' AS DateTime), N'Faza finałowa 1/2 MŚ U20', -1, -1)
SET IDENTITY_INSERT [dbo].[Matches] OFF
INSERT [dbo].[Teams] ([name]) VALUES (N'Arabia Saudyjska U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Argentyna U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Ecuador U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Francja U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Honduras U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Japonia U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Katar U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Kolumbia U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Korea Po?udniowa U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Korea Południowa U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Mali U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Meksyk U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Nigeria U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Norwegia U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Nowa Zelandia U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Panama U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Polska U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Portugalia U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'RPA U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Senegal U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Tahiti U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Ukraina U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Urugwaj U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'USA U20')
INSERT [dbo].[Teams] ([name]) VALUES (N'Włochy U20')
ALTER TABLE [dbo].[Matches]  WITH CHECK ADD FOREIGN KEY([away])
REFERENCES [dbo].[Teams] ([name])
GO
ALTER TABLE [dbo].[Matches]  WITH CHECK ADD FOREIGN KEY([home])
REFERENCES [dbo].[Teams] ([name])
GO
USE [master]
GO
ALTER DATABASE [FootlendarDB] SET  READ_WRITE 
GO
