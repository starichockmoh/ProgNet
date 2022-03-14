import s from "./Paginator.module.css";
import React, {useCallback, useState} from "react";
import {useDispatch} from "react-redux";
import 'antd/dist/antd.css';
import {Button} from 'antd';
import {setCurrentFriendPageACType, setCurrentPageACType} from "../../../Redux/Reducers/UsersReducer";

type PageChangeFunctionType = () => void
type ActionCreatorUsers = (page:number) => setCurrentPageACType
type ActionCreatorFriends = (FriendsPage:number) => setCurrentFriendPageACType
type PropsType = {
    onPageChanged: (p:number) => void
    totalItems: number
    pageSize: number
    currentPage: number
    currentPageAc: any
    // ActionCreatorUsers | ActionCreatorFriends
}
export const Paginator: React.FC<PropsType>= ({onPageChanged, totalItems, pageSize,currentPage, currentPageAc}) => {
    let pagesCount: number = Math.ceil(totalItems / pageSize)
    const dispatch = useDispatch()
    const setCurrentPage = useCallback((page:number) => {
        dispatch(currentPageAc(page))},[dispatch,currentPageAc])
    let [pageLeft,changePageLeft] = useState(1)
    let [pageRight, changePageRight] = useState(10)

    let changeSizeToRight: PageChangeFunctionType = () => {
        setCurrentPage(pageLeft+10)
        changePageRight(pageRight+10)
        changePageLeft(pageLeft+10)
        onPageChanged(pageLeft+10)
    }

    let changeSizeToLeft: PageChangeFunctionType = () => {
        setCurrentPage(pageLeft-10)
        changePageRight(pageRight-10)
        changePageLeft(pageLeft-10)
        onPageChanged(pageLeft-10)
    }

    let MaxLeft: PageChangeFunctionType = () => {
        changePageRight(10)
        changePageLeft(1)
        setCurrentPage(1)
        onPageChanged(1)

    }

    let MaxRight: PageChangeFunctionType = () => {
        changePageLeft(pagesCount-10)
        changePageRight(pagesCount)
        setCurrentPage(pagesCount-10)
        onPageChanged(pagesCount-10)
    }

    let pages: Array<number> = []
    for (let i = pageLeft; i <= pageRight; i++) {
        pages.push(i)
        if (i >= pagesCount) {
            break
        }
    }
    return <div className={s.PaginatorBlock}>
        {(pageLeft === 1)? null
            : <span><Button onClick={pageLeft > 1? MaxLeft: undefined}>back</Button>
                <Button onClick={pageLeft > 1? changeSizeToLeft: undefined}>←</Button></span> }
        {pages.map(p => {
            return <Button onClick={() => {
                setCurrentPage(p)
                onPageChanged(p)
            }} className={currentPage === p? s.currentPage: s.Page}>{p}</Button>
        })}
        <Button onClick={pageRight < pagesCount? changeSizeToRight: undefined}>→</Button>
        <Button onClick={pageRight < pagesCount? MaxRight: undefined}>next</Button>
    </div>
}
