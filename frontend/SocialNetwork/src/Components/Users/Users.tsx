import React from "react";
import UserElement from "./UserElement/UserElement";
import {Paginator} from "../Common/Paginator/Paginator";
import Preloader from "../Common/Preloader/Preloader";
import UsersSearch from "./UserElement/UsersSearch";
import styles from './Users.module.css'

import {useDispatch, useSelector} from "react-redux";
import {
    getFollowIsProgressing,
    getIsFetching,
    getTotalUsersCount,
    getUsersSuper
} from "../../Redux/Selectors/UsersSelector";
import {AppStateType} from "../../Redux/ReduxStore";
import {FollowOrUnfollow, requestUsers, UserActions} from "../../Redux/Reducers/UsersReducer";

type UsersPropsType = {
    currentPage: number
    pageSize: number
}

type QueryType = {
    term?: string,
    friend?: string,
    page?: string

}

const Users: React.FC<UsersPropsType> = ({pageSize, currentPage}) => {
    const dispatch = useDispatch()
    const filter = useSelector((state: AppStateType) => state.UsersPage.filter)
    const onPageChanged = (p: number) => {
        dispatch(requestUsers(p, pageSize, filter.friend, filter.term))
    }
    const FollowOrUnfollowCallback = (userId: number, follow: boolean, isFriend: boolean) => {
        dispatch(FollowOrUnfollow(userId, follow, isFriend))
    }
    const totalUsersCount = useSelector(getTotalUsersCount)
    const followIsProgressing = useSelector(getFollowIsProgressing)
    const isFetching = useSelector(getIsFetching)
    const users = useSelector(getUsersSuper)

    let UsersArray = users? users.map(u => <UserElement key={u.id}
                                                        user={u}
                                                        followIsProgressing={followIsProgressing}
                                                        FollowOrUnfollow={FollowOrUnfollowCallback}/>): []

    return <div>
        <Paginator
            onPageChanged={onPageChanged}
            totalItems={totalUsersCount}
            pageSize={pageSize}
            currentPage={currentPage}
            currentPageAc={UserActions.setCurrentPageAC}

        />
        <UsersSearch pageSize={pageSize}/>
        {isFetching ? <Preloader/> : <div>{UsersArray}</div>}
    </div>
}

export default Users